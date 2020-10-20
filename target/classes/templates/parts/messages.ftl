<#if successMessage??>
    <div class="alert alert-success" role="alert">
        ${successMessage?html}
    </div>
</#if>
<#if errorMessage??>
    <div class="alert alert-danger" role="alert">
        ${errorMessage?html}
    </div>
</#if>