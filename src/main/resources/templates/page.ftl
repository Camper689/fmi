<#import "parts/common.ftl" as common><@common.html page.title?if_exists>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">${page?title?if_exists}</li>
    </ul>

    <#include "parts/messages.ftl" />

    <a href="/page/${page.id?c}" class="btn btn-default">Редагувати цю сторінку</a>

    <div class="row">
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-4 btn-folder-space-min">
                    <a class="btn btn-folder btn-lg" href="/pages/2-semestr">2 семестр</a>
                </div>
                <div class="col-sm-4 btn-folder-space-min">
                    <a class="btn btn-folder btn-lg" href="/pages/1-semestr">1 семестр</a>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-4 btn-folder-space-min">
                    <a class="btn btn-folder btn-lg" href="/pages/2-semestr">2 семестр</a>
                </div>
                <div class="col-sm-4 btn-folder-space-min">
                    <a class="btn btn-folder btn-lg" href="/pages/1-semestr">1 семестр</a>
                </div>
            </div>
        </div>

        <div class="col-sm-3">
            <div class="block block-margin">
                ${sidebar}
                <ul class="page-menu"><li><a style="padding-left: 12px" href="/pages/osvitnia-diialnist">Освітня діяльність</a></li><li><a style="padding-left: 24px" href="/pages/perelik-zalikiv">Перелік заліків</a></li><li><a style="padding-left: 36px" href="/pages/2-semestr">2 семестр</a></li><li><a style="padding-left: 36px" href="/pages/1-semestr">1 семестр</a></li><li><a style="padding-left: 24px" href="/pages/zaochna-forma-navchannia">Заочна форма навчання</a></li><li><a style="padding-left: 36px" href="/pages/rozklady-navchannia">Розклади навчання</a></li><li><a style="padding-left: 48px" href="/pages/litnia-sesiia">Літня сесія</a></li><li><a style="padding-left: 48px" href="/pages/zymova-sesiia">Зимова сесія</a></li><li><a style="padding-left: 48px" href="/pages/nastanovcha-sesiia">Настановча сесія</a></li><li><a style="padding-left: 36px" href="/pages/hrafik-navchalnoho-protsesu-de2a4a2c-78cb-4bd9-898b-448d6d88683f">Графік навчального процесу</a></li><li><a style="padding-left: 24px" href="/pages/rozklad-ekzameniv">Розклад екзаменів</a></li><li><a style="padding-left: 36px" href="/pages/1-semestr-a9ecc567-5db9-46e8-9011-9492f046088f">1 семестр</a></li><li><a style="padding-left: 36px" href="/pages/2-semestr-5e7414c4-9986-41a1-a7cb-9cf0e7b98a0d">2 семестр</a></li><li><a style="padding-left: 24px" href="/pages/robochi-navchalni-plany">Робочі навчальні плани</a></li><li><a style="padding-left: 36px" href="/pages/bakalavr-1c39cc84-ddfb-4a87-8989-a6e52930b748">Бакалавр</a></li><li><a style="padding-left: 48px" href="/pages/prykladna-matematyka">Прикладна математика</a></li><li><a style="padding-left: 48px" href="/pages/serednia-osvita-informatyka-d5fc3bd7-56a0-4d2c-a6e3-f56a2b97a4d8">Середня освіта (Інформатика)</a></li><li><a style="padding-left: 48px" href="/pages/profesiina-osvita-kompiuterni-tekhnolohii-6694920e-8933-4b19-9911-8535ee980139">Професійна освіта (Комп'ютерні технології)</a></li><li><a style="padding-left: 48px" href="/pages/kompiuterni-nauky-skorochenyi-termin">Комп'ютерні науки (скорочений термін)</a></li><li><a style="padding-left: 48px" href="/pages/serednia-osvita-matematyka-e206ebd2-4773-4dc7-af0f-fd852cbbee4c">Середня освіта. Математика</a></li><li><a style="padding-left: 60px" href="/pages/informatyka-7d082e6a-71f6-4d21-a7e2-58ec2e1a868d">Інформатика</a></li><li><a style="padding-left: 60px" href="/pages/ekonomika-7ecd00f0-a3fa-442f-80b2-42df4de6558a">Економіка</a></li><li><a style="padding-left: 60px" href="/pages/fizyka-9842f145-d6a1-4cea-884e-47b279c1749e">Фізика</a></li><li><a style="padding-left: 48px" href="/pages/kompiuterni-nauky-2b7ca8b7-8cc8-49b4-8910-353e29c2784b">Комп'ютерні науки</a></li><li><a style="padding-left: 48px" href="/pages/inzheneriia-prohramnoho-zabezpechennia">Інженерія програмного забезпечення</a></li><li><a style="padding-left: 48px" href="/pages/profesiina-osvita-tsyfrovi-tekhnolohii">Професійна освіта (Цифрові технології)</a></li><li><a style="padding-left: 36px" href="/pages/mahistr">Магістр</a></li><li><a style="padding-left: 48px" href="/pages/kompiuterni-nauky-be159a35-a395-49e6-bcf3-ffed15c2ad69">Комп'ютерні науки</a></li><li><a style="padding-left: 48px" href="/pages/profesiina-osvita-kompiuterni-tekhnolohii-40cc5dec-fd45-498d-9d2a-c8d8f1e50b25">Професійна освіта (Комп'ютерні технології)</a></li><li><a style="padding-left: 48px" href="/pages/serednia-osvita-informatyka-60579b07-cde8-4e28-8497-dabd307c4268">Середня освіта (Інформатика)</a></li><li><a style="padding-left: 48px" href="/pages/serednia-osvita-matematyka-7f4b1557-92c7-47e9-b1b3-b2f96c290d60">Середня освіта (Математика)</a></li><li><a style="padding-left: 60px" href="/pages/informatyka-6070d541-748f-43ec-9c83-ce1a0fb8bc3e">Інформатика</a></li><li><a style="padding-left: 60px" href="/pages/fizyka-caef8cde-5355-4e31-8104-c2698cd857fd">Фізика</a></li><li><a style="padding-left: 60px" href="/pages/ekonomika-3065e82d-86c9-4417-8121-60dd6e73a970">Економіка</a></li><li><a style="padding-left: 60px" href="/pages/matematyka-62592463-fa68-4cc5-9755-6ebba6ee6064">Математика</a></li><li><a style="padding-left: 48px" href="/pages/prykladna-matematyka-8daab163-1835-4dc5-9d51-96ef5b9da074">Прикладна математика</a></li><li><a style="padding-left: 48px" href="/pages/profesiina-osvita-tsyfrovi-tekhnolohii-0bad5b2e-e6fc-40c3-9ac8-3e205b7dd2c5">Професійна освіта (Цифрові технології)</a></li><li><a style="padding-left: 24px" href="/pages/osvitni-prohramy">Освітні програми</a></li><li><a style="padding-left: 36px" href="/pages/mahistr-94e0fbd1-3bf5-4bc7-aafb-98b64ae5719d">Магістр</a></li><li><a style="padding-left: 48px" href="/pages/01510-profesiina-osvita-kompiuterni-tekhnolohii-3fd89280-7437-4c32-9a8f-b0a1f111f394">015.10 Професійна освіта (Комп'ютерні технології)</a></li><li><a style="padding-left: 48px" href="/pages/01409-serednia-osvita-informatyka-317a515b-4768-45ba-ba7b-878cff0f098f">014.09 Середня освіта (Інформатика)</a></li><li><a style="padding-left: 48px" href="/pages/122-kompiuterni-nauky-ff2bf935-017e-4a28-a5e6-01c8ec204fe0">122 Комп'ютерні науки</a></li><li><a style="padding-left: 48px" href="/pages/113-prykladna-matematyka-a64d6669-8c78-4aa3-a0ab-5db771b0a913">113 Прикладна математика</a></li><li><a style="padding-left: 48px" href="/pages/01404-serednia-osvita-matematyka-f869c10b-80a5-4eaa-87d4-37f613a30068">014.04 Середня освіта (Математика)</a></li><li><a style="padding-left: 36px" href="/pages/bakalavr-8f90492c-140a-4a8a-a2c6-468689a19b79">Бакалавр</a></li><li><a style="padding-left: 48px" href="/pages/122-kompiuterni-nauky">122 Комп'ютерні науки</a></li><li><a style="padding-left: 48px" href="/pages/01404-serednia-osvita-matematyka">014.04 Середня освіта (Математика)</a></li><li><a style="padding-left: 48px" href="/pages/01409-serednia-osvita-informatyka">014.09 Середня освіта (Інформатика)</a></li><li><a style="padding-left: 48px" href="/pages/01510-profesiina-osvita-kompiuterni-tekhnolohii">015.10 Професійна освіта (Комп'ютерні технології)</a></li><li><a style="padding-left: 48px" href="/pages/113-prykladna-matematyka">113 Прикладна математика</a></li><li><a style="padding-left: 48px" href="/pages/new-121-inzheneriia-prohramnoho-zabezpechennia">New! 121 Інженерія програмного забезпечення</a></li><li><a style="padding-left: 24px" href="/pages/rozklad-derzhavnykh-ekzameniv">Розклад державних екзаменів</a></li><li><a style="padding-left: 36px" href="/pages/zaochna">Заочна</a></li><li><a style="padding-left: 36px" href="/pages/denna">Денна</a></li><li><a style="padding-left: 24px" href="/pages/pidhotovka-bakalavriv">Підготовка бакалаврів</a></li><li><a style="padding-left: 24px" href="/pages/pidhotovka-mahistriv">Підготовка магістрів</a></li><li><a style="padding-left: 36px" href="/pages/vybirkovi-komponenty">Вибіркові компоненти</a></li><li><a style="padding-left: 36px" href="/pages/praktychna-pidhotovka">Практична підготовка</a></li><li><a style="padding-left: 36px" href="/pages/derzhavna-atestatsiia">Державна атестація</a></li><li><a style="padding-left: 36px" href="/pages/oboviazkovi-komponenty">Обов'язкові компоненти</a></li><li><a style="padding-left: 24px" href="/pages/rozpodil-navchalnoho-navantazhennia">Розподіл навчального навантаження</a></li><li><a style="padding-left: 24px" href="/pages/reitynhovi-spysky-d07c5084-bfbb-420b-a778-8b50e0bbf72f">Рейтингові списки </a></li><li><a style="padding-left: 36px" href="/pages/reitynhovi-spysky-studentiv">Рейтингові списки студентів</a></li><li><a style="padding-left: 36px" href="/pages/pravyla-pryznachennia-akademichnykh-stypendii-studentam">Правила призначення академічних стипендій студентам</a></li><li><a style="padding-left: 24px" href="/pages/hrafik-navchalnoho-protsesu">Графік навчального процесу</a></li><li><a style="padding-left: 24px" href="/pages/informatsiia-pro-mozhlyvosti-pratsevlashtuvannia">Інформація про можливості працевлаштування</a></li><li><a style="padding-left: 24px" href="/pages/indyvidualni-navchalni-plany">Індивідуальні навчальні плани</a></li><li><a style="padding-left: 24px" href="/pages/yakist-osvity">Якість освіти</a></li></ul></div></div></div>

</@common.html>