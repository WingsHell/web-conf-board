package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.config.JwtTokenUtil;
import fr.sma.webconfboard.entities.*;
import fr.sma.webconfboard.services.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/generate-token", produces = {MediaType.APPLICATION_JSON_VALUE })
    //@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<AuthToken> register(@RequestBody Login loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
    }

    /*// ------------------- SignUp a User -------------------------------------------

    @PostMapping(value = "/sign-up", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<AuthToken> SignUp(@RequestBody User user) {
        logger.info("Creating User : {}", user);

        if(userService.isUserExist(user)) {
            logger.error("Unable to create. A User with username {} already exist",user.getUsername());
            return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Unable to create and signup. A User with username " +
                    user.getUsername() + " already exist.",userService.save(user));

        } else if( user == null ) {
            return new ApiResponse<AuthToken>(HttpStatus.NOT_FOUND.value(), "Unable to create and signup. User undefined.",userService.save(user));
        } else {
            userService.save(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            //ResponseEntity.created(uri)
                    //.body(user);
            final String token = jwtTokenUtil.generateToken(user);
            return new ApiResponse<AuthToken>(200, "User saved with token successfully.",new AuthToken(token, userFinal.getUsername()));

        }
    }*/

}
