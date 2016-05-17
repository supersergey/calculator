<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Calculator">

<div class="container" style="max-width: 500px;">

    <h2>Enter expression to calculate</h2>

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

    <form action="/getExpressions" method="post">
        <input type="text" class="form-control" placeholder="Enter expression" name="expression" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:20px; margin-bottom:20px;">
            Calculate
        </button>
    </form>

    <div style="margin-top:20px;">

        <#if expressions??>
            <#if expressions?size gt 0>

                <#if calculated = true>
                    <h3 style="text-align: center">${expressions[0].expression} = ${expressions[0].result}</h3>
                </#if>

                <#if calculated == true && expressions?size gt 1 || calculated == false>

                    <h4>Your previous results</h4>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <td><strong>Date</strong></td>
                            <td><strong>Expression</strong></td>
                            <td><strong>Result</strong></td>
                        </tr>
                        </thead>

                        <#assign x = 0>
                        <#if calculated = true && expressions?size gt 1>
                            <#assign x = 1>
                        </#if>
                        <#list x..expressions?size-1 as i>
                            <tr>
                                <td>${expressions[i].pubDateStr}</td>
                                <td>${expressions[i].expression}</td>
                                <td>${expressions[i].result}</td>
                            </tr>
                        </#list>
                    </table>
                    <a href="/clear">Clear history</a>
                </#if>
            </#if>
        </#if>
    </div>

</div>

<div class="footer" style="position:absolute; top:0; right:0; margin: 20px 20px 0px 0px;">
    <a href="/logout">Logout</a>
</div>

</@layout.masterTemplate>