package com.mbti.mindpairing.api.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService {
//        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

//    private final UserRepository userRepository;
//    private final HttpSession httpSession;
//
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        User user = saveOrUpdate(attributes);
//        httpSession.setAttribute("user", new SessionUser(user));
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }
//
//    private User saveOrUpdate(OAuthAttributes attributes) {
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                .orElse(attributes.toEntity());
//
//        return userRepository.save(user);
//    }
}
