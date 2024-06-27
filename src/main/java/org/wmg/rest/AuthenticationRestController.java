package org.wmg.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wmg.mapper.MenuMapper;
import org.wmg.security.jwt.JWTFilter;
import org.wmg.security.jwt.TokenProvider;
import org.wmg.security.model.Menu;
import org.wmg.service.MenuService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author wmg
 * @date 2024/6/18
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        AuthenticationManager object = authenticationManagerBuilder.getObject();
        Authentication authenticate = object.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authenticate, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }


    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }


    @Autowired
    private MenuService menuService;


    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/select")
    public IPage<Menu> selectOne(@RequestParam String title, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        if (title.isEmpty()) {
            return new Page<>();
        }
        Page<Menu> page = new Page<>(pageNum, pageSize);
        page.setSearchCount(true);
        return menuMapper.selectByMenuPage(page, title);
    }
    @GetMapping("/delete")
    public Integer deleteOne(@RequestParam String id){
        if (id.isEmpty()) {
            return 0;
        }
        return menuService.deleteOne(StringUtils.trimAllWhitespace(id));
    }

    @DeleteMapping("/api/{id}/delete")
    public Integer deleteResource(@PathVariable("id") String id) {
        // Example response message
        return menuMapper.deleteOne(id);
    }

    @GetMapping("/page")
    public Page<Menu> selectPage(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String title) {
        Page<Menu> rowPage = new Page(page, pageSize);
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (title != null && !"".equals(title)) {
            queryWrapper.like(Menu::getTitle, title);
        }
        rowPage = this.menuMapper.selectPage(rowPage, queryWrapper);
        return rowPage;
    }

    @PostMapping("/edit")
    public void updateItem(@RequestBody Menu menu) {
        // 根据传入的id和更新后的项目对象进行保存逻辑
        // ...
        this.menuMapper.updateById(menu);
    }

    @GetMapping("/exists")
    public Boolean existsItem(String title) {
        return this.menuMapper.existsItem(title.toLowerCase()).isPresent();
    }


    /**
     * 可以查任何表，任何任何匹配value
     *
     * @param tableName
     * @param columnName
     * @param likeValue
     * @return
     */
    @GetMapping("/any")
    public JSONArray selectAnyTable(String tableName, String columnName, String likeValue) {
        // 调用查询方法
        List<Map<String, Object>> results = menuMapper.selectAnyTable(tableName, columnName, likeValue);

// 创建一个空的JSONArray
        JSONArray jsonArray = new JSONArray();

// 将查询结果转换为JSONArray
        for (Map<String, Object> result : results) {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
