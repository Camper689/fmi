<#import "parts/common.ftl" as common><@common.html 'Розклад занять'>
<ul id="breadcrumbs" class="breadcrumbs">
    <li><a href="/"><i class="fa fa-home"></i></a></li>
    <li class="current">Розклад занять</li>
</ul>

<#include "parts/messages.ftl" />

<button class="btn btn-default" href="javascript:void(0)" onclick="modal('#setTeacherInfoModal', null);">Редагувати
    інформацію
</button>

<div class="middle-wrapper">
    <div class="block timetable-main-sort sort-margin block-padding">
        <h3 class="text-center">Щоб переглянути розклад, виберіть</h3>
        <div class="sort">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <select name="group" id="group-select" class="form-control">
                        <option value="">Групи</option>
                        <#list allGroups as group>
                        <option value="${group.id?c}">${group.name?html}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <h4 class="text-center">або</h4>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <select name="teacher" id="teacher-select" class="form-control d-block">
                        <option value="">Викладачі</option>
                        <#list allTeachers as teacher>
                        <option value="${teacher.id?c}">${teacher.getFullName()?html}</option>
                        </#list>
                    </select>
                </div>
            </div>
        </div>
        <div class="alert alert-info"><b>Останнє оновлення розкладу: </b>03.05.19, 11:26</div>
    </div>

</div>
<script>
            $('#teacher-select').on('change', function() {
                window.location.href = "/teacher/" + this.value;
            });

            $('#group-select').on('change', function() {
                window.location.href = "/groups/" + this.value;
            });
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            });

</script>
</div>
</@common.html>