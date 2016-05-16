<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Register">
<div class="container" style="max-width: 500px;">
    <h2>Register</h2>
    <#if error??>
        <div class="error">
            <strong>Error:</strong> ${error}
        </div>
    </#if>

    <form class="form-login" action="/register" method="post"
    ">
    <label for="userName" class="sr-only">Username</label>
    <input type="username" name="username" id="username" size="30" maxlength="50" class="form-control" placeholder="User name"
           value="${username!}" required autofocus>
    <label for="email" class="sr-only">Email</label>
    <input type="email" placeholder="Email" id="email" name="email" size="30" class="form-control" value="${email!}" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="password" size="30" id="inputPassword" class="form-control" placeholder="Password"
           required>
    <label for="inputPassword" class="sr-only">Password once again</label>
    <input type="password" name="password2" size="30" id="inputPassword2" class="form-control" placeholder="Password2"
           required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:20px; margin-bottom:20px;">
        Register
    </button>
    </form>
    or <a href="/login" style="margin-top: 20px;">Login</a>
</div>
</@layout.masterTemplate>