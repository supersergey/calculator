<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Sign In">

<div class="container" style="max-width: 500px;">

    <h2>Sign In</h2>
    <#if message??>
        <div class="success">
        ${message}
        </div>
    </#if>
    <#if error??>
        <div class="error">
            <strong>Error:</strong> ${error}
        </div>
    </#if>

    <form class="form-signin" action="/login" method="post">
        <label for="userName" class="sr-only">Username</label>
        <input type="userName" id="inputUserName" name="username" size="30" maxlength="50" class="form-control" placeholder="User name"
               value="${username!}" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" size="30" id="inputPassword" class="form-control" placeholder="Password"
               required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:20px; margin-bottom:20px;">Sign in</button>
    </form>
    or <a href="/register" style="margin-top: 20px;">Register</a>
</div>

</@layout.masterTemplate>