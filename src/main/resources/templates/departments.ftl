<#import "parts/common.ftl" as common><@common.html 'Галерея'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Кафедри</li>
    </ul>

    <#include "parts/messages.ftl" />

    <button class="btn btn-default" href="javascript:void(0)" onclick="modal('#addDepartmentModal', null);">Додати кафедру</button>

    <div class="wrapper">
        <div class="department-block">
        <#list allDepartments as department>
            <div class="department">
                <a class="dep-link" href="/departments/${department.id?c}">${department.name?html}</a>
                <div class="dep-members">
                    <div class="dep-chief">
                        <div class="dep-chief-photo">
                            <div class="dep-chief-photo-bg">
                                <#if department.hasChief() && department.chief.hasAvatar()>
                                <a href="/teacher/${department.chief.id?c}">
                                    <img src="/image/${department.chief.avatar.getFullName()}" alt="${department.chief.getShortName()}">
                                </a>
                                </#if>
                            </div>
                        </div>
                        <div class="dep-chief-info">
                            <#if department.hasChief()>
                                <div class="dep-chief-name">
                                    <a href="/teacher/${department.chief.id?c}">${department.chief.getFullName()}</a>
                                 </div>
                                 <div class="dep-chief-status"><#if department.chief.hasStatus()>${department.chief.status.name?html}</#if></div>
                            </#if>
                        </div>
                    </div>
                    <div class="dep-members-link">
                        <a class="btn btn-default" title="Викладачі кафедри" href="/departments/${department.id?c}/teachers">
                            <i class="fa fa-users"></i>
                        </a>
                    </div>
                </div>
            </div>
        </#list>
        </div>
    </div>

    <@common.createModal 'addDepartmentModal' 'Додати кафедру' 'Додати'>
        <@common.postForm '/departments/add'>
            <input class="form-control" name="name" placeholder="Введіть назву кафедри">
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editDepartmentModal' 'Редагувати кафедру' 'Зберегти'>
        <@common.postForm '/departments/edit'>
            <input name="id" type="hidden">
            <input class="form-control" name="name" placeholder="Введіть нову назву кафедри">
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteDepartmentModal' 'Видалити кафедру' 'Видалити'>
        <@common.postForm '/departments/delete'>
            <input name="id" type="hidden">
            Ви точно хочете видалити кафедру &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>