<#import "parts/common.ftl" as common><@common.html 'Новини'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Новини</li>
    </ul>

    <#include "parts/messages.ftl" />

    <@common.postForm "/posts/add">
        <button class="btn btn-default">Створити новий пост</button>
    </@common.postForm>

    <div class="posts-block">
        <div class="row">
            <#list page.content as post>
            <div class="col-sm-4">
                <div class="block">
                    <div class="mini-post-block">
                        <a href="/posts/${post.id?c}">
                            <div class="mini-post-image">
                                <#if post.image??><img src="/image/${post.image.getFullName()}" alt="${post.title?if_exists}"></#if>
                                <div class="mini-post-cat"><#if post.category??>${post.category.name?html}</#if></div>
                            </div>
                        </a>
                        <div class="mini-post-content">
                            <a href="/posts/${post.id?c}">
                                <h2 class="mini-post-title">${post.title?if_exists}</h2>
                            </a>
                            <div class="mini-post-text">${post.getPreview()}</div>
                            <div class="mini-post-date">${post.getFormatDate()}</div>
                        </div>
                    </div>
                </div>
            </div>
            </#list>
        </div>

        <#assign pageNumber = page.number + 1 />
        <div class="text-center">
            <ul class="pagination">
                <#if pageNumber gt 1><li><a href="/posts">« Перша</a></li>
                <li><a rel="prev" href="/posts?page=${pageNumber - 1}">‹ Назад</a></li></#if>
                <#list 1..page.totalPages as x>
                <li<#if pageNumber == x> class="active"</#if>><a href="<#if pageNumber == x>#<#else>/posts?page=${x}</#if>">${x}</a></li>
                </#list>
                <#if pageNumber lt page.totalPages>
                <li><a rel="next" href="/posts?page=${pageNumber + 1}">Вперед ›</a></li>
                <li><a href="/posts?page=${page.totalPages}">Остання »</a></li>
                </#if>
            </ul>
        </div>
    </div>
</@common.html>