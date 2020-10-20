<#import "parts/common.ftl" as common><@common.html 'Новини''>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Новини</li>
    </ul>

    <#include "parts/messages.ftl" />

    <div class="posts-block">
        <div class="row">
            <div class="col-sm-4">
                <div class="block">
                    <div class="mini-post-block">
                        <a href="/posts/48">
                            <div class="mini-post-image">
                                <img src="/uploads/post/image/48/mini_Plagiariarism.jpg" alt="Mini plagiariarism">
                                <div class="mini-post-cat">Новини</div>
                            </div>
                        </a>
                        <div class="mini-post-content">
                            <a href="/posts/48">
                                <h2 class="mini-post-title">ДИПЛОМНІ РОБОТИ БЕЗ ПЛАГІАТУ!</h2>
                            </a>
                            <div class="mini-post-text">З березня місяця у Рівненському державному гуманітарному університеті функціонуватиме система «StrikePlagiarism» для перевірки випускних дипломних...</div>
                            <div class="mini-post-date">04 Березня 2020</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center">
            <ul class="pagination">
                <li class="active"><a href="#">1</a></li>
                <li><a rel="next" href="/posts?page=2">2</a></li>
                <li><a href="/posts?page=3">3</a></li>
                <li><a href="/posts?page=4">4</a></li>
                <li><a href="/posts?page=5">5</a></li>
                <li><a rel="next" href="/posts?page=2">Вперед ›</a></li>
                <li><a href="/posts?page=5">Остання »</a></li>
            </ul>
        </div>
    </div>

</@common.html>