<#import "parts/common.ftl" as common><@common.html post.title?if_exists?html>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li><a href="/posts">Новини</a></li>
        <li class="current">${post.title?if_exists?html}</li>
    </ul>

    <#include "parts/messages.ftl" />

    <a href="/posts/update?post=${post.id?c}" class="btn btn-default">Редагувати цей пост</a>

    <div class="row">
        <div class="col-sm-8">
            <div class="post-block">
                <div class="block">
                    <div class="post-image">
                        <#if post.image??><img src="/image/${post.image.getFullName()}" alt="${post.title?if_exists}"></#if>
                        <#if post.category??><div class="post-cat">${post.category.name}</div></#if>
                        <div class="post-date">${post.getFormatDate()}</div>
                    </div>
                    <div class="post-content">
                        <h1 class="post-title">${post.title?if_exists}</h1>
                        <div class="content-text-block">
                            <div class="post-text">
                                ${post.body?if_exists}
                            </div>
                        </div>
                    </div>
                    <#if post.album??>
                    <div class="zoom-gallery album-images">
                        <#list post.album.images as image>
                        <div class="image-block">
                            <a href="/image/${image.getFullName()}">
                                <img title="${post.album.name}" src="/image/${image.getFullName()}" alt="${image.getAlt()}">
                            </a>
                        </div>
                        </#list>
                    </div>
                    <script>$(document).ready(function() {
                      $('.zoom-gallery').magnificPopup({
                        delegate: 'a',
                        type: 'image',
                        closeOnContentClick: false,
                        closeBtnInside: false,
                        mainClass: 'mfp-with-zoom mfp-img-mobile',
                        gallery: {
                          enabled: true
                        },
                        zoom: {
                          enabled: true,
                          duration: 300, // don't foget to change the duration also in CSS
                          opener: function(element) {
                            return element.find('img');
                          }
                        }

                      });
                      $('.popup-youtube').magnificPopup({
                        disableOn: 700,
                        type: 'iframe',
                        mainClass: 'mfp-fade',
                        removalDelay: 160,
                        preloader: false,

                        fixedContentPos: false
                      });
                    });</script>
                    </#if>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="block sidebar-margin">
                <div class="block-title"><h4>Підписка на новини</h4></div>
                <div class="block-padding">
                    <div class="form-group">
                        <input class="form-control" placeholder="Ім’я">
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="Email">
                    </div>
                    <div class="block-center">
                        <button class="btn btn-primary btn-transparent"><i class="fa fa-paper-plane"></i> Підписатися</button>
                    </div>
                </div>
            </div>

            <div class="block sidebar-margin">
                <div class="mini-post-block">
                    <a href="/posts/47">
                        <div class="mini-post-image">
                            <img src="/uploads/post/image/47/mini_zno.jpg" alt="Mini zno">
                            <div class="mini-post-cat">Новини</div>
                        </div>
                    </a>
                    <div class="mini-post-content">
                        <a href="/posts/47">
                            <h2 class="mini-post-title">НАШИМ МАЙБУТНІМ АБІТУРІЄНТАМ</h2>
                        </a>
                        <div class="mini-post-text">До уваги випускників шкіл та майбутніх абітурієнтів факультету математики та інформатики!
Рівненський державний гуманітарний...
                        </div>
                        <div class="mini-post-date">19 Лютого 2020</div>
                    </div>
                </div>


                <div class="mini-post-block">
                    <a href="/posts/47">
                        <div class="mini-post-image">
                            <img src="/uploads/post/image/47/mini_zno.jpg" alt="Mini zno">
                            <div class="mini-post-cat">Новини</div>
                        </div>
                    </a>
                    <div class="mini-post-content">
                        <a href="/posts/47">
                            <h2 class="mini-post-title">НАШИМ МАЙБУТНІМ АБІТУРІЄНТАМ</h2>
                        </a>
                        <div class="mini-post-text">До уваги випускників шкіл та майбутніх абітурієнтів факультету математики та інформатики!
Рівненський державний гуманітарний...
                        </div>
                        <div class="mini-post-date">19 Лютого 2020</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@common.html>