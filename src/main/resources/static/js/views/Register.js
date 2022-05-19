import CreateView from "../createView.js"
export default function Register() {
    // language=HTML
    return `
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8"/>
            <title>Register</title>
        </head>
        <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-md-6">
                    <div class="form-holder my-5">
                        <h1 id="register-page-title">Register</h1>
                        <form>
                            <div>
                                <label for="username">Username</label>
                                <input class="form-control" id="username" name="username" type="text" required/>
                                <p id="usernameEmptyMessage">Provide Username</p>
                            </div>
                            <div>
                                <label for="email" class="email-label">Email</label>
                                <input class="form-control" id="email" name="email" type="email" required>
                                <p id="emailEmptyMessage">Email cannot be empty</p>
                                <p id="emailInvalidFormatMessage">Provide Email Format Example (Codeup.@yahoo.com)</p>
                            </div>
                            <div>
                                <label for="password">Password</label>
                                <input class="form-control" id="password" name="password" type="password"
                                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                       title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                       required/>
                                <p id="passwordEmptyMessage">Must Provide Password</p>
                                <div id="message">
                                    <p>Password must contain the following:</p>
                                    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                                    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                                    <p id="number" class="invalid">A <b>number</b></p>
                                    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
                                </div>
                            </div>
                            <div>
                                <a href="/login" data-link>Already have an account?</a>
                            </div>
                            <div>
                                <p id="emailOrUsernameExists">Error 66 Letter Format Already Exists</p>
                                <button id="register-btn" class="btn btn-light mt-3" type="button" disabled>Register
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </body>
        </html>
    `;
}
export function RegisterEvent() {
    registerButtonListener();
}

export function registerButtonListener()  {
    $("#register-btn").click(function () {
        let username = $("#username").val();
        let email = $("#email").val();
        let password = $("#password").val();
        let usernameEmptyMessage = document.getElementById("usernameEmptyMessage");
        let emailEmptyMessage = document.getElementById("emailEmptyMessage");

        if (username === "" || email === "") {
            usernameEmptyMessage.style.display = "block";
            usernameEmptyMessage.style.color = "red";
            emailEmptyMessage.style.display = "block";
            emailEmptyMessage.style.color = "red";
            return;
        }

        const newUser = {
            username: username,
            email: email,
            password: password
        }

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newUser)
        }

        fetch("http://localhost:8080/api/users/create", request)
            .then(response => {
                console.log(response)
                console.log(response.status);
                if (response.status === 500) {
                    document.getElementById("emailOrUsernameExists").style.display = "block";
                    document.getElementById("emailOrUsernameExists").style.color = "red";
                    return;
                }
                CreateView("/login");
            })

    })
}