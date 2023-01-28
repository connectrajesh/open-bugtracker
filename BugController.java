import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private AuthorizationService authzService;

    @PostMapping("/")
    public Bug createBug(@RequestBody Bug bug, @RequestHeader("Authorization") String jwt) {
        // Verify the JWT and extract the user from it
        User user = authzService.verifyJWT(jwt);

        // Check if the user has the necessary roles to create a bug
        if (!authzService.isAuthorized(user, "REPORTER")) {
            throw new UnauthorizedException();
        }

        // Create the bug and return it
        return bugService.createBug(bug);
    }

    @GetMapping("/{id}")
    public Bug getBug(@PathVariable String id, @RequestHeader("Authorization") String jwt) {
        // Verify the JWT and extract the user from it
        User user = authzService.verifyJWT(jwt);

        // Check if the user has the necessary roles to view the bug
        if (!authzService.isAuthorized(user, "REPORTER", "DEVELOPER", "MANAGER")) {
            throw new UnauthorizedException();
        }

        // Get the bug and return it
        return bugService.getBug(id);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable String id, @RequestBody Bug bug, @RequestHeader("Authorization") String jwt) {
        // Verify the JWT and extract the user from it
        User user = authzService.verifyJWT(jwt);

        // Check if the user has the necessary roles to update the bug
        if (!authzService.isAuthorized(user, "DEVELOPER", "MANAGER")) {
            throw new UnauthorizedException();
        }

        // Update the bug and return it
        return bugService.updateBug(id, bug);
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable String id, @RequestHeader("Authorization") String jwt) {
        // Verify the JWT and extract the user from it
        User user = authzService.verifyJWT(jwt);

        // Check if the user has the necessary roles to delete the bug
        if (!authzService.isAuthorized(user, "MANAGER")) {
            throw new UnauthorizedException();
        }

        // Delete the bug
        bugService.deleteBug(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody Credentials credentials) {
        // Verify the credentials and return a JWT
        return authService.login(credentials);
    }
}
