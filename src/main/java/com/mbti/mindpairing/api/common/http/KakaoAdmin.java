package com.mbti.mindpairing.api.common.http;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbti.mindpairing.api.login.dto.KakaoUser;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class KakaoAdmin {
    public KakaoUser.getAccessTokenResponse kakaoGetAccessTokenResponse(String code) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", "96cdbc5044b9b9866c31f0792de1c20a");
        map.add("redirenct_uri", "http://http://ec2-3-35-99-11.ap-northeast-2.compute.amazonaws.com:9001/v1/login/kakao");
//        map.add("redirenct_uri", "http://localhost:9001/v1/login/kakao");
        map.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request
                = new HttpEntity<>(map, headers);
        ResponseEntity<KakaoUser.getAccessTokenResponse> response
                = restTemplate.exchange(url, HttpMethod.POST, request, KakaoUser.getAccessTokenResponse.class);

        return response.getBody();
    }

    public Map<String, Object> returnAccountMap(String accessToken) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", " Bearer " + accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Object>> responseMap = mapper.readValue(response.getBody(), Map.class);

        return responseMap.get("kakao_account");
    }
}
