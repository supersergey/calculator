<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title} | Calculator</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
		<div class="page">
	  		<h1>Calculator</h1>
		  	<div class="navigation">
		  	<#if user??>
		    	<a href="/logout">sign out [${user.username}]</a> |
		  	<#else>
                <a href="/login">login</a> |
				<a href="/register">register</a> |
		  	</#if>
		  	</div>
		  	<div class="body">
		  		<#nested />
		  	</div>
		  	<div class="footer">
			    Online calculator demo by Sergey Tolokunsky
		  	</div>
		</div>
    </body>
    </html>
</#macro>