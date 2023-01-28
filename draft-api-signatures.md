API Signatures:

Login:
Endpoint: /api/login
Method: POST
Request Body: {username: string, password: string}
Response: {jwt: string}

Create Bug:
Endpoint: /api/bugs
Method: POST
Request Header: {Authorization: Bearer <JWT>}
Request Body: {title: string, description: string, assignedTo: string, priority: string}
Response: {id: string, title: string, description: string, assignedTo: string, priority: string, createdBy: string}

Read Bug:
Endpoint: /api/bugs/{id}
Method: GET
Request Header: {Authorization: Bearer <JWT>}
Response: {id: string, title: string, description: string, assignedTo: string, priority: string, createdBy: string}

Update Bug:
Endpoint: /api/bugs/{id}
Method: PUT
Request Header: {Authorization: Bearer <JWT>}
Request Body: {title: string, description: string, assignedTo: string, priority: string}
Response: {id: string, title: string, description: string, assignedTo: string, priority: string, createdBy: string}

Delete Bug:
Endpoint: /api/bugs/{id}
Method: DELETE
Request Header: {Authorization: Bearer <JWT>}
Response: {message: string}
Note: The above API signatures are an example and can be modified based on the specific requirements of the project. Additionally, the roles of the user are extracted from the JWT and are used to authorize the user for specific actions.
