<#import "parts/common.ftl" as common><@common.html 'Список секцій'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Список секцій</li>
    </ul>

    <div class="block block-margin">
        <button class="btn btn-default" onclick="modal('#addSectionModal', null)">Додати секцію</button>

        <table class="table mt-2">
            <thead>
                <th>Назва</th>
                <th>Клас</th>
                <th></th>
                <th>Головна сторінка</th>
            </thead>
            <tbody>
                <#list allSections as section>
                    <tr style="background: #BBBBBB">
                        <th>${section.name?html}</th>
                        <th>${section.className?html}</th>
                        <th><button class="btn btn-sm btn-default" onclick="modal('#editSectionModal', {id: ${section.id?c}, name: '${section.name?html}', className: '${section.className?html}'})">Редагувати</button></th>
                        <th><button class="btn btn-sm btn-default" onclick="modal('#addSectionPageModal', {id: ${section.id?c}, sectionName: '${section.name?html}'})">Додати сторінку</button></th>
                        <th>
                            <@common.postForm "/section/setMainPage">
                            <input type="hidden" name="id" value="${section.id?c}" />
                            <select name="page" class="form-control" onchange="$(this).parent().submit()">
                                <option value=""<#if !section.mainPage??> selected</#if>>Оберіть головну сторінку</option>
                                <#list section.pages as p>
                                <option value="${p.id?c}"<#if section.mainPage?? && section.mainPage.id == p.id> selected</#if>>${p.name?if_exists}</option>
                                </#list>
                            </select>
                            </@common.postForm>
                        </th>
                    </tr>
                    <#if section.pages?has_content>
                    <#assign printed = false />
                    <#list section.pages as page>
                    <tr>
                        <th><#if !printed>Сторінки:<#assign printed = true /></#if></th>
                        <th>${page.name}</th>
                        <th><a href="/page/${page.id?c}">Редагувати</a></th>
                        <th><a href="/pages/${page.url}">Перегляд</a></th>
                    </tr>
                    </#list>
                    </#if>
                </#list>
            </tbody>
        </table>
    </div>

    <@common.createModal 'addSectionPageModal' 'Додати сторінку до секції' 'Додати'>
        <@common.postForm "/section/addPage">
            Назва секції: <span name="sectionName"></span>
            <input name="id" type="hidden" />
            <input class="form-control" name="name" placeholder="Введіть назву сторінки" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'addSectionModal' 'Додати секцію' 'Додати'>
        <@common.postForm "/section/add">
            <input class="form-control" name="name" placeholder="Введіть назву секції" />
            <input class="form-control" name="className" placeholder="Введіть клас секції" />
            <small>Список класів можна знайти за <a href="https://fontawesome.com/v4.7.0/icons/">посиланням</a></small>
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editSectionModal' 'Редагувати секцію' 'Зберегти'>
        <@common.postForm "/section/edit">
            <input class="form-control" name="name" placeholder="Введіть назву секції" />
            <input class="form-control" name="className" placeholder="Введіть клас секції" />
            <small>Список класів можна знайти за <a href="https://fontawesome.com/v4.7.0/icons/">посиланням</a></small>
            <input type="hidden" name="id" />
        </@common.postForm>
    </@common.createModal>
</@common.html>