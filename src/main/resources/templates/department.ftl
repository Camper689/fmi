<#import "parts/common.ftl" as common><@common.html department.name?html>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li><a href="/departments">Кафедри</a></li>
        <li class="current">${department.name?html}</li>
    </ul>

    <#include "parts/messages.ftl" />

    <div class="row">
        <div class="col-sm-8">
            <a href="/departments/page?department=${department.id?c}">Додати сторінку</a>
            <#assign number = 1 />
            <#list pages as page>
                <a href="/departments/page?page=${page.id?c}">Редагувати сторінку</a>
                <div class="block <#if number gt 1>block-padding </#if>sidebar-margin">
                    <#if page.fromChief && department?? && department.hasChief()>
                        <div class="chief-block">
                            <div class="chief-photo">
                                <div class="chief-photo">
                                    <#if department.chief.hasAvatar()><img src="/image/${department.chief.avatar.getFullName()}" alt="${department.chief.getShortName()?html}"></#if>
                                </div>
                            </div>
                            <div class="chief-info">
                                <div class="chief-title">
                                    <a href="/teachers/${department.chief.id?c}">${department.chief.getFullName()?html}</a>
                                </div>
                                <div class="chief-desc">Завідуючий(-ча) кафедри</div>
                            </div>
                        </div>
                        <div class="chief-content">
                            ${page.body}
                        </div>
                    <#else>
                        <h1 class="page-title">${page.title?html}</h1>
                        <div class="content-text-block">${page.body}</div>
                    </#if>
                </div>
                <#assign number = number + 1 />
            </#list>
        </div>

        <div class="col-sm-4">
            <div class="block sidebar-margin">
                <div class="dep-teacher-sidebar">
                    <div class="dep-teacher-sidebar-photos">
                        <#assign number = 1 />
                        <#list teachers as teacher>
                            <#if number lt 5>
                                <a title="${teacher.getFullName()?html}" href="/teachers/${teacher.id?c}">
                                    <div class="dep-teacher-sidebar-photo">
                                        <#if teacher.hasAvatar()><img width="100%" height="100%" src="/image/${teacher.avatar.getFullName()}" alt="${teacher.getShortName()?html}"></#if>
                                    </div>
                                </a>
                            </#if>
                            <#assign number = number + 1 />
                        </#list>
                    </div>
                    <div class="dep-teacher-sidebar-link">
                        <a href="/departments/${department.id?c}/teachers">Викладачі кафедри</a>
                    </div>
                </div>
            </div>
            <div class="block sidebar-margin">
                <button class="btn btn-default" onclick="modal('#addSectionModal', null)">Додати розділ</button>

                <div class="sidebar-menu">
                    <ul>
                        <#list sections as section>
                        <li>
                            <span class="text-bold">
                                ${section.name?html}
                                <button class="btn btn-default" onclick="modal('#editSectionModal', {id: ${section.id?c}, name: '${section.name?html}'})">Змінити назву</button>
                                <button class="btn btn-default" onclick="modal('#deleteSectionModal', {id: ${section.id?c}, name: '${section.name?html}'})">Видалити розділ</button>
                                <a href="/departments/page?section=${section.id?c}" class="btn btn-default">Додати сторінку</a>
                            </span>
                        </li>
                            <#list section.pages as pg>
                                <li><a class="child" href="/departments/${department.id?c}/pages/${pg.id?c}">${pg.title?html}</a></li>
                            </#list>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <@common.createModal 'addSectionModal' 'Додати розділ' 'Додати'>
        <@common.postForm "/departments/addSection">
            <input name="department" type="hidden" value="${department.id?c}" />
            <input name="name" class="form-control" placeholder="Введіть назву розділу" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editSectionModal' 'Редагувати розділ' 'Зберегти'>
        <@common.postForm "/departments/editSection">
            <input name="department" type="hidden" value="${department.id?c}" />
            <input name="id" type="hidden" />
            <input name="name" class="form-control" placeholder="Введіть нову назву розділу" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteSectionModal' 'Видалити розділ' 'Видалити'>
        <@common.postForm '/departments/deleteSection'>
            <input name="id" type="hidden" />
            <input name="departmentId" type="hidden" value="${department.id?c}" />
            Ви точно хочете видалити розділ &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>