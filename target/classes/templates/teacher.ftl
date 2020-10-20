<#import "parts/common.ftl" as common><@common.html teacher.getFullName() true>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li><a href="/departments">Кафедри</a></li>
        <li><a href="/departments/${department.id?c}">Кафедра "Кафедра вищої математики"</a></li>
        <li class="current">${teacher.getFullName()}</li>
    </ul>

    <#include "parts/messages.ftl" />

    <button class="btn btn-default" href="javascript:void(0)" onclick="modal('#setTeacherInfoModal', null);">Редагувати інформацію</button>

    <div class="wrapper">
        <div class="block block-margin">
            <div class="teacher-page-block">
                <div class="teacher-page-photo-block">
                    <div class="teacher-page-photo img-circle">
                        <div class="teacher-page-photo-bg">
                            <#if teacher.hasAvatar()><img height="100%" src="/image/${teacher.avatar.getFullName()}"></#if>
                        </div>
                    </div>
                </div>
                <div class="teacher-page-info-block">
                    <div class="teacher-page-info">
                        <div class="teacher-page-name">
                            <h3>${teacher.getFullName()}</h3>
                        </div>
                        <div class="teacher-page-status">
                            <h4 class="text-gray"><#if teacher.hasStatus()>${teacher.status.name?html}</#if></h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="teacher-page-about-block">
                <div class="teacher-page-about teacher-page-about-limit">
                    ${teacher.info}
                </div>
                <div class="block-margin text-center">
                    <button class="btn btn-default" id="about-spoiler">
                        <i class="fa fa-chevron-down"></i> Більше інформації
                    </button>
                </div>
            </div>
        </div>
        <div class="block block-margin">
            <div class="teacher-page-timetable-block">
                <div class="row">
                    <div class="col-sm-6">
                        <h4>Список занять</h4>
                    </div>
                    <div class="col-sm-6">
                        <a class="btn btn-default btn-sm pull-right button-toolbox-left-margin" href="/teachers/3/next_week">
                            <i class="fa fa-arrow-right"></i> Наступний тиждень
                        </a>
                    </div>
                </div>
                <#assign days=["Понеділок","Вівторок","Середа","Четвер","П'ятниця","Субота"] />
                <div class="teacher-page-timetable">
                <#list days as d>
                    <div class="col-sm-4">
                        <div class="teacher-page-day-info">
                            <div class="pull-left"><b>${d}</b></div>
                            <div class="pull-right text-gray">12.10.20</div>
                        </div>
                        <div class="teacher-page-group">
                            <ul>
                                <li><b>ПМ-21</b> Історія України</li>
                                <li><b>ПМ-21</b> Історія України</li>
                                <li>Hahaha</li>
                                <li>Hahaha</li>
                                <li><b>ПМ-32</b> Історія України</li>
                            </ul>
                        </div>
                    </div>
                </#list>
                </div>
            </div>
        </div>
    </div>

    <@common.createModal 'setTeacherInfoModal' 'Редагувати інформацію' 'Зберегти'>
        <@common.postForm '/teacher/setInfo'>
            <input name="id" type="hidden" value="${teacher.id?c}">
            <textarea name="info" style="display: none"></textarea>
            <div data-quill-editor="teacherInfo">
                ${teacher.info?if_exists}
            </div>
        </@common.postForm>
    </@common.createModal>
</@common.html>