package com.haulmont.sample.petclinic.web.controller.registration;

import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.TrustedClientService;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import com.haulmont.sample.petclinic.service.RegistrationService;
import java.util.function.Supplier;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Inject
    protected TrustedClientService trustedClientService;
    @Inject
    protected RegistrationService registrationService;
    @Inject
    protected Configuration configuration;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  ResponseEntity<Void> registerOwnerViaPublicForm(
            OwnerRegistrationRequest request
    ) {

        return runAuthenticated(() -> {
            registrationService.register(request.toOwnerRegistration());
            return ResponseEntity.ok().build();
        });
    }


    private <T> ResponseEntity<T> runAuthenticated(Supplier<ResponseEntity<T>> doIt) {
        String trustedClientPassword = configuration.getConfig(WebAuthConfig.class).getTrustedClientPassword();
        UserSession systemSession = trustedClientService.getSystemSession(trustedClientPassword);
        SecurityContext securityContext = new SecurityContext(systemSession);
        SecurityContext previousSecurityContext = AppContext.getSecurityContext();
        AppContext.setSecurityContext(securityContext);
        try {
            return doIt.get();
        }
        catch (Exception e) {
            log.error("Error while execution: " + e.getMessage(), e);
            throw e;
        }
        finally {
            AppContext.setSecurityContext(previousSecurityContext);
        }
    }

}
