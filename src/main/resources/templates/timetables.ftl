<#import "parts/common.ftl" as common><@common.html 'Розклад занять'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Розклад занять</li>
    </ul>

    <#include "parts/messages.ftl" />

    <button class="btn btn-default" href="javascript:void(0)" onclick="modal('#setTeacherInfoModal', null);">Редагувати інформацію</button>

    <div class="middle-wrapper">
            <div class="block timetable-main-sort sort-margin block-padding">
                <h3 class="text-center">Що б переглянути розклад, виберіть</h3>
                <div class="sort"><div class="row"><div class="col-sm-6 col-sm-offset-3"><div class="sort-block"><div class="btn-group bootstrap-select form-control sort-select"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="group-select" title="Групи"><span class="filter-option pull-left">Групи</span>&nbsp;<span class="bs-caret"><span class="caret"></span></span></button><div class="dropdown-menu open"><div class="bs-searchbox"><input type="text" class="form-control" autocomplete="off"></div><ul class="dropdown-menu inner" role="menu"><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Групи</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">І-11</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">І-21</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="4"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">І-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="5"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">І-41</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="6"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">І-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="7"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КН-11</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="8"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КН-21</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="9"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КН-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="10"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КН-41</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="11"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КТ-11</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="12"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КТ-21</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="13"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КТ-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="14"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">КТ-41</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="15"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-11</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="16"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МЕІ-21</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="17"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МЕІ-41</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="18"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МЕФ-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="19"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МЕФІ-41</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="20"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МЕФІ-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="21"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МІ-22</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="22"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МІ-32</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="23"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МІ-42</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="24"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-І-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="25"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">МІФ-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="26"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-КН-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="27"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-КН-61</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="28"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-М-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="29"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-М-51 (2 р.н. )</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="30"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-МІ-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="31"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-МФ-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="32"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-ПМ-51</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="33"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">М-ПМ-61</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="34"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">ПМ-21</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="35"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">ПМ-31</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div><select name="teacher" id="group-select" class="form-control selectpicker sort-select" data-live-search="true" title="Групи" tabindex="-98"><option class="bs-title-option" value="">Групи</option><option value="">Групи</option><option value="1">І-11</option>
        <option value="2">І-21</option>
        <option value="3">І-31</option>
        <option value="4">І-41</option>
        <option value="31">І-51</option>
        <option value="36">КН-11</option>
        <option value="39">КН-21</option>
        <option value="7">КН-31</option>
        <option value="48">КН-41</option>
        <option value="6">КТ-11</option>
        <option value="13">КТ-21</option>
        <option value="41">КТ-31</option>
        <option value="45">КТ-41</option>
        <option value="5">М-11</option>
        <option value="8">МЕІ-21</option>
        <option value="44">МЕІ-41</option>
        <option value="14">МЕФ-31</option>
        <option value="19">МЕФІ-41</option>
        <option value="25">МЕФІ-51</option>
        <option value="11">МІ-22</option>
        <option value="17">МІ-32</option>
        <option value="23">МІ-42</option>
        <option value="34">М-І-51</option>
        <option value="38">МІФ-31</option>
        <option value="43">М-КН-51</option>
        <option value="35">М-КН-61</option>
        <option value="32">М-М-51</option>
        <option value="46">М-М-51 (2 р.н. )</option>
        <option value="42">М-МІ-51</option>
        <option value="47">М-МФ-51</option>
        <option value="33">М-ПМ-51</option>
        <option value="29">М-ПМ-61</option>
        <option value="12">ПМ-21</option>
        <option value="18">ПМ-31</option></select></div></div></div></div><h4 class="text-center">або</h4><div class="row"><div class="col-sm-6 col-sm-offset-3"><div class="sort-block"><div class="btn-group bootstrap-select form-control sort-select"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="teacher-select" title="Викладачі"><span class="filter-option pull-left">Викладачі</span>&nbsp;<span class="bs-caret"><span class="caret"></span></span></button><div class="dropdown-menu open"><div class="bs-searchbox"><input type="text" class="form-control" autocomplete="off"></div><ul class="dropdown-menu inner" role="menu"><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Викладачі</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Антонюк Микола Степанович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Бабич Стефанія Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="4"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Батишкіна Юлія Валеріївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="5"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Белешко Дмитро Тимофійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="6"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Березняк Валентина Іванівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="7"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Бордюк Микола Анатолійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="8"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Віднічук  Микола Антонович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="9"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Войтович Ігор Станіславович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="10"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Вороницька Віра Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="11"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Гаврюсєва Тетяна Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="12"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Галатюк Михайло Юрійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="13"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Галатюк Юрій Михайлович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="14"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Генсіцька-Антонюк Наталія Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="15"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Герман Наталя Володимирівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="16"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Глінчук Юлія  Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="17"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Гнедко Наталя Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="18"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Грипич Світлана  Никонорівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="19"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Гульчук Віталій Анатолійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="20"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Гур'янова Ірина Едуардівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="21"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Дейнега О. В.</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="22"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Демчик Світлана Петрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="23"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Джеджера К. В.</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="24"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Димченко Надія  Станіславівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="25"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Заглинська Любов Василівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="26"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Златів Леся Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="27"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Кирик Тетяна Анатоліївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="28"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Кирилецька Галина Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="29"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Кириченко Наталія Анатоліївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="30"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Клекоць Ганна Яківна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="31"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Коваль Володимир Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="32"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Крайчук Олександр Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="33"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Крівцов Валентин Валерійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="34"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Кулаков Руслан Станіславович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="35"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Кундеус Оксана  Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="36"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Левчук Василь  Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="37"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Лесняк Віталій  Юрійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="38"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Ляшук Тарас Григорович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="39"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Максимцев Юрій  Романович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="40"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Малафіїк Іван  Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="41"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Малаш Катерина Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="42"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Марач Віктор Сильвестрович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="43"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Матвєєва Вікторія  Станіславівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="44"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Матусевич Костянтин Миколайович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="45"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Мащенко Володимир  Андрійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="46"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Мислінчук Володимир Олександрович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="47"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Мороз Ігор Петрович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="48"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Мосієвич Олександр Степанович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="49"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Музичук Катерина Петрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="50"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Назарук Марія Володимирівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="51"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Немеш Олена  Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="52"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Новоселецький  Микола Юхимович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="53"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Ногачевська Інна Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="54"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Остапчук Наталія Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="55"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Остапчук Микола Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="56"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Павелків Ольга Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="57"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Павлова Наталія Степанівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="58"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Пальчевський Руслан Степанович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="59"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Панчук Ірина  Іванівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="60"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Панюк Тетяна  Петрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="61"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Пасічник Ядвіга Августівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="62"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Петренко Сергій Вікторович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="63"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Петрівський Ярослав Борисович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="64"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Петрівський Борис Петрович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="65"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Петрушко Юлія Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="66"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Полюхович Наталія Вікторівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="67"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Приймак Ольга Петрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="68"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Присяжнюк Ігор Михайлович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="69"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Руденко  Володимир Миколайович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="70"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Садовець Інна Василівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="71"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сапіліді Тамара Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="72"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Семещук  Ігор Лаврентійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="73"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сідлецький  Валентин Олександрович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="74"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сілков Валерій Васильович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="75"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сілкова Ельвіра Орестівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="76"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сінчук Алеся Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="77"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Созонюк Ольга  Степанівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="78"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сорочинська Тетяна  Адамівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="79"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Стельмашук Жанна Григорівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="80"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Ступницька Ніна  Іванівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="81"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сяська Наталія Андріївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="82"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сяський Андрій Олексійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="83"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Сяський Володимир Андрійович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="84"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Терешко Юрій Миколайович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="85"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Тимчук Михайло Вікторович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="86"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Тищук Віталій  Іванович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="87"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Черній Валерій  Альбертович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="88"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шахрайчук Микола Іович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="89"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шевчук Катерина Миколаївна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="90"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шидловський Андрій Іванович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="91"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шинкарчук Назар Володимирович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="92"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шліхта Ганна Олександрівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="93"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шроль Тетяна Степанівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="94"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Шугаєва Людмила  Михайлівна</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="95"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Юсенко Андрій  Степанович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="96"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">Ястремський Сергій Вікторович</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div><select name="teacher" id="teacher-select" class="form-control selectpicker sort-select" data-live-search="true" title="Викладачі" tabindex="-98"><option class="bs-title-option" value="">Викладачі</option><option value="">Викладачі</option><option value="6">Антонюк Микола Степанович</option>
        <option value="5">Бабич Стефанія Михайлівна</option>
        <option value="1">Батишкіна Юлія Валеріївна</option>
        <option value="44">Белешко Дмитро Тимофійович</option>
        <option value="86">Березняк Валентина Іванівна</option>
        <option value="62">Бордюк Микола Анатолійович</option>
        <option value="106">Віднічук  Микола Антонович</option>
        <option value="101">Войтович Ігор Станіславович</option>
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
        <option value="93">Дейнега О. В.</option>
        <option value="56">Демчик Світлана Петрівна</option>
        <option value="96">Джеджера К. В.</option>
        <option value="98">Димченко Надія  Станіславівна</option>
        <option value="72">Заглинська Любов Василівна</option>
        <option value="61">Златів Леся Михайлівна</option>
        <option value="10">Кирик Тетяна Анатоліївна</option>
        <option value="47">Кирилецька Галина Миколаївна</option>
        <option value="92">Кириченко Наталія Анатоліївна</option>
        <option value="51">Клекоць Ганна Яківна</option>
        <option value="50">Коваль Володимир Васильович</option>
        <option value="2">Крайчук Олександр Васильович</option>
        <option value="111">Крівцов Валентин Валерійович</option>
        <option value="89">Кулаков Руслан Станіславович</option>
        <option value="83">Кундеус Оксана  Миколаївна</option>
        <option value="84">Левчук Василь  Васильович</option>
        <option value="108">Лесняк Віталій  Юрійович</option>
        <option value="29">Ляшук Тарас Григорович</option>
        <option value="80">Максимцев Юрій  Романович</option>
        <option value="90">Малафіїк Іван  Васильович</option>
        <option value="22">Малаш Катерина Миколаївна</option>
        <option value="58">Марач Віктор Сильвестрович</option>
        <option value="97">Матвєєва Вікторія  Станіславівна</option>
        <option value="110">Матусевич Костянтин Миколайович</option>
        <option value="87">Мащенко Володимир  Андрійович</option>
        <option value="100">Мислінчук Володимир Олександрович</option>
        <option value="13">Мороз Ігор Петрович</option>
        <option value="85">Мосієвич Олександр Степанович</option>
        <option value="30">Музичук Катерина Петрівна</option>
        <option value="40">Назарук Марія Володимирівна</option>
        <option value="88">Немеш Олена  Миколаївна</option>
        <option value="103">Новоселецький  Микола Юхимович</option>
        <option value="99">Ногачевська Інна Олександрівна</option>
        <option value="31">Остапчук Наталія Олександрівна</option>
        <option value="109">Остапчук Микола Васильович</option>
        <option value="46">Павелків Ольга Миколаївна</option>
        <option value="32">Павлова Наталія Степанівна</option>
        <option value="95">Пальчевський Руслан Степанович</option>
        <option value="70">Панчук Ірина  Іванівна</option>
        <option value="74">Панюк Тетяна  Петрівна</option>
        <option value="43">Пасічник Ядвіга Августівна</option>
        <option value="33">Петренко Сергій Вікторович</option>
        <option value="3">Петрівський Ярослав Борисович</option>
        <option value="57">Петрівський Борис Петрович</option>
        <option value="63">Петрушко Юлія Олександрівна</option>
        <option value="34">Полюхович Наталія Вікторівна</option>
        <option value="49">Приймак Ольга Петрівна</option>
        <option value="25">Присяжнюк Ігор Михайлович</option>
        <option value="66">Руденко  Володимир Миколайович</option>
        <option value="112">Садовець Інна Василівна</option>
        <option value="55">Сапіліді Тамара Михайлівна</option>
        <option value="79">Семещук  Ігор Лаврентійович</option>
        <option value="104">Сідлецький  Валентин Олександрович</option>
        <option value="42">Сілков Валерій Васильович</option>
        <option value="52">Сілкова Ельвіра Орестівна</option>
        <option value="20">Сінчук Алеся Михайлівна</option>
        <option value="69">Созонюк Ольга  Степанівна</option>
        <option value="77">Сорочинська Тетяна  Адамівна</option>
        <option value="65">Стельмашук Жанна Григорівна</option>
        <option value="81">Ступницька Ніна  Іванівна</option>
        <option value="48">Сяська Наталія Андріївна</option>
        <option value="7">Сяський Андрій Олексійович</option>
        <option value="15">Сяський Володимир Андрійович</option>
        <option value="105">Терешко Юрій Миколайович</option>
        <option value="53">Тимчук Михайло Вікторович</option>
        <option value="68">Тищук Віталій  Іванович</option>
        <option value="71">Черній Валерій  Альбертович</option>
        <option value="16">Шахрайчук Микола Іович</option>
        <option value="94">Шевчук Катерина Миколаївна</option>
        <option value="37">Шидловський Андрій Іванович</option>
        <option value="35">Шинкарчук Назар Володимирович</option>
        <option value="36">Шліхта Ганна Олександрівна</option>
        <option value="38">Шроль Тетяна Степанівна</option>
        <option value="76">Шугаєва Людмила  Михайлівна</option>
        <option value="82">Юсенко Андрій  Степанович</option>
        <option value="78">Ястремський Сергій Вікторович</option></select></div></div></div></div></div><div class="alert alert-info"><b>Останнє оновлення розкладу: </b>03.05.19, 11:26</div></div>

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