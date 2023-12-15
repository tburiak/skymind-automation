# SkyMind Test Automation Framework

### Steps to run tests

```
 ./gradlew clean test --tests "runner.TestSuiteRunner"
 ```

## Manual test cases

### User Creation

#### Valid User Creation:
1. Open the User Management section.
2. Click on the "Create User" button.
3. Enter a valid first name, last name, and a unique email address.
4. Click the "Submit" button.
5. Verify that the user is created successfully.
6. Check that the default role assigned is "Basic Access."

#### Duplicate Email Prevention:
1. Open the User Management section.
2. Click on the "Create User" button.
3. Enter an email address that already exists in the system.
4. Click the "Submit" button.
5. Verify that the system displays an error message preventing the creation of a user with a duplicate email.

#### Missing Information:

1. Open the User Management section.
2. Click on the "Create User" button.
3. Leave one or more of the required fields (first name, last name, email) blank.
4. Click the "Submit" button.
5. Verify that the system prompts for the missing information.

### User Update

#### Update User Information:

1. Open the User Management section.
2. Select an existing user.
3. Click on the "Edit" or "Update" button.
4. Modify the first name, last name, and email address.
5. Click the "Save" button.
6. Verify that the changes are reflected in the user details.

#### Update Access Roles:

1. Open the User Management section.
2. Select an existing user.
3. Click on the "Edit" or "Update" button.
4. Add or remove access roles (excluding Temperature Check and Shipment Management simultaneously).
5. Click the "Save" button.
6. Verify that the roles are updated successfully.


#### Remove All Access Roles:

1. Open the User Management section.
2. Select a user with roles assigned.
3. Click on the "Edit" or "Update" button.
4. Remove all access roles.
5. Click the "Save" button.
6. Verify that the user no longer has any access roles.