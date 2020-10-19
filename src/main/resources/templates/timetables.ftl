<#import "parts/common.ftl" as common><@common.html 'Розклад занять'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Розклад занять</li>
    </ul>

    <#include "parts/messages.ftl" />

    <button class="btn btn-default" href="javascript:void(0)" onclick="modal('#setTeacherInfoModal', null);">Редагувати інформацію</button>

    <div class="middle-wrapper">
        <div class="middle-wrapper">
            <div class="block timetable-main-sort sort-margin block-padding">
                <h3 class="text-center">Що б переглянути розклад, виберіть</h3>
                <div class="sort">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <div class="sort-block">
                                <div class="btn-group bootstrap-select form-control sort-select dropup">
                                    <button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="group-select" title="Групи" aria-expanded="false">
                                        <span class="filter-option pull-left">Групи</span>&nbsp;
                                        <span class="bs-caret"><span class="caret"></span></span>
                                    </button>
                                    <div class="dropdown-menu open" style="max-height: 314px; overflow: hidden; min-height: 134px;">
                                        <select name="teacher" id="group-select" class="form-control selectpicker sort-select" data-live-search="true" title="Групи" tabindex="-98">
                                            <option class="bs-title-option" value="">Групи</option>
                                            <option value="">Групи</option>
                                            <option value="1">І-11</option>
                                            <option value="2">І-21</option>
                                            <option value="3">І-31</option>
                                            <option value="4">І-41</option>
                                            <option value="12">ПМ-21</option>
                                            <option value="18">ПМ-31</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h4 class="text-center">або</h4>
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <div class="sort-block">
                                    <div class="btn-group bootstrap-select form-control sort-select dropup">
                                        <button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="teacher-select" title="Викладачі" aria-expanded="false">
                                            <span class="filter-option pull-left">Викладачі</span>&nbsp;
                                            <span class="bs-caret"><span class="caret"></span></span>
                                        </button>
                                        <div class="dropdown-menu open" style="max-height: 402px; overflow: hidden; min-height: 134px;">
                                            <div class="bs-searchbox">
                                                <input type="text" class="form-control" autocomplete="off">
                                            </div>
                                            <ul class="dropdown-menu inner" role="menu" style="max-height: 348px; overflow-y: auto; min-height: 80px;">
                                                <li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Викладачі</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
                                                <li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Антонюк Микола Степанович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
                                                <li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Бабич Стефанія Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
                                                <li data-original-index="4"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Батишкіна Юлія Валеріївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
                                                <li data-original-index="5"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Белешко Дмитро Тимофійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>
                                            </ul>
                                        </div>
                                        <select name="teacher" id="teacher-select" class="form-control selectpicker sort-select" data-live-search="true" title="Викладачі" tabindex="-98">
                                            <option class="bs-title-option" value="">Викладачі</option>
                                            <option value="">Викладачі</option><option value="6">Антонюк Микола Степанович</option>
                                            <option value="5">Бабич Стефанія Михайлівна</option>
                                            <option value="1">Батишкіна Юлія Валеріївна</option>
                                            <option value="44">Белешко Дмитро Тимофійович</option>
                                            <option value="86">Березняк Валентина Іванівна</option>
                                            <option value="62">Бордюк Микола Анатолійович</option>
                                            <option value="106">Віднічук  Микола Антонович</option>
                                            <option value="18">Вороницька Віра Михайлівна</option>
                                            <option value="26">Гаврюсєва Тетяна Олександрівна</option>
                                            <option value="64">Галатюк Михайло Юрійович</option>
                                            <option value="67">Галатюк Юрій Михайлович</option>
                                            <option value="45">Генсіцька-Антонюк Наталія Олександрівна</option>
                                            <option value="102">Герман Наталя Володимирівна</option>
                                            <option value="60">Глінчук Юлія  Олександрівна</option>
                                            <option value="28">Гнедко Наталя Михайлівна</option>
                                            <option value="91">Грипич Світлана  Никонорівна</option>
                                            <option value="27">Гульчук Віталій Анатолійович</option>
                                            <option value="73">Гур'янова Ірина Едуардівна</option>
                                            <option value="78">Ястремський Сергій Вікторович</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="alert alert-info"><b>Останнє оновлення розкладу: </b>03.05.19, 11:26</div>
                </div>
            </div>
        </div>
        <script>
            $('#teacher-select').on('change', function() {
                window.location.href = "/teachers/" + this.value;
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