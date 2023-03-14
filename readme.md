<!-- @format -->

# How to Build & Run the Application

1. First go to your project directory in command line [where is your project located ?]
2. Then in the next step you have to create jar file for that, using `./mvnw package` [for MAC OS] , this will create jar file for our application.
3. jar file is created in the target sub-directory
4. Now go to target sub directory as jar was created inside of it , i.e cd target
5. Now run the jar file in there. Use command `java -jar xxx.jar` [ xxx is the name of your created jar file.]
6. Go to `http://localhost:[port]` [port is the portnumber] e.g localhost:8080

But if you are using vscode (like me) install the spring boot plugin and just run from the vscode side bar.

# How it works

Once you are able to build & run the application, Use your favorite Api Platform (Mine is Postman)

1. Create a post request to `http://localhost:[portnumber]/accounts` with a json body `{ "balance": 25000 }` this will create an account with an initial balance of 25000 the result will look like `{"id":52,"balance":25000.0}`
2. Incase you entered an invalid amount like -25000 the response will be a Not Acceptable (406) with `Invalid Balance: -25000.0`
3. Create a few other accounts. Make a GET request to `/accounts/{id}` e.g `/accounts/52` and the response will be like HTTP 200 `{"id":52,"balance":25000.0}`. If you entered an Id that is not in the db then you will get a 404 response e.g `Account of id 520 was not found!`
4. To make a transfer make a post request to `/transfers` with the body of the transfer request e.g `{ "from": 52, "to": 1, "amount": 2500 }` and a response like `{"id":9,"from":52,"to":1,"amount":2500.0}` will appear in case of an error the appropriate HTTP response will be returned.

- if Account does not exist (from or to) a HTTP 404 will be sent `Account 520 not found`
- A Transaction is created in transfers table with the transaction info
- The amount is deducted from the `from account` and credited to the `to account`
- If amount to be deducted is more than the available balance, a 400 response `Insufficient Balance on Account 52` is returned
