@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    public Bug createBug(Bug bug, String jwt) {
        User user = authzService.verifyJWT(jwt);
        if (!authzService.isAuthorized(user, "create_bug")) {
            throw new AuthorizationException("User is not authorized to create bugs");
        }
        bug.setCreatedBy(user.getUsername());
        return bugRepository.save(bug);
    }

    public Bug readBug(String id, String jwt) {
        User user = authzService.verifyJWT(jwt);
        if (!authzService.isAuthorized(user, "read_bug")) {
            throw new AuthorizationException("User is not authorized to read bugs");
        }
        return bugRepository.findById(id).orElseThrow(() -> new BugNotFoundException());
    }

    public Bug updateBug(Bug bug, String jwt) {
        User user = authzService.verifyJWT(jwt);
        if (!authzService.isAuthorized(user, "update_bug")) {
            throw new AuthorizationException("User is not authorized to update bugs");
        }
        return bugRepository.save(bug);
    }

    public void deleteBug(String id, String jwt) {
        User user = authzService.verifyJWT(jwt);
        if (!authzService.isAuthorized(user, "delete_bug")) {
            throw new AuthorizationException("User is not authorized to delete bugs");
        }
        bugRepository.deleteById(id);
    }
}

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid username or password");
        }
        // generate and return JWT
    }
}

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public User verifyJWT(String jwt) {
        // verify JWT and return user
    }

    public String[] extractRoles(String jwt) {
        // extract roles from JWT and return as string array
    }

    public boolean isAuthorized(User user, String requiredPermission) {
        // check if user has the required permission
    }
}
