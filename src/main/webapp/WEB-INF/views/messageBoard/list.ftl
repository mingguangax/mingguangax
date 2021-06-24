<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>狼途汽车互联网商户平台</title>
    <#include "/common/link.ftl"/>
    <link rel="stylesheet" href="/static/css/index_css.css">
</head>
<body>
<div class="nav">
    <div class="nav-div">
        <div class="row justify-content-between" >
            <div class="col-md-4 border-right" style="padding: 0px;" >
                <h4>狼途汽车服务平台</h4>
                <h5 style="color:#434343;font-size: 18px">连锁运营顾问 / 高级服务专家</h5>
            </div>
            <div class="col-md-3 category" style="padding-left: 30px">
                <button class="btn btn-primary-cus ">售卖</button>
                <button class="btn btn-primary-cus ">保养</button>
                <button class="btn btn-primary-cus ">修理</button>
                <button class="btn btn-primary-cus ">美容</button>
                <button class="btn btn-primary-cus ">配件</button>
                <button class="btn btn-primary-cus ">售后</button>
            </div>
            <div class="col-md-1" style="text-align: center;padding: 20px;">
                <button class="btn btn-primary-full" type="button" id="btn-appointment">
                    马上预约
                </button>

            </div>
            <div class="col-md-4 tel"  style="padding: 15px;">
                <h2 style="vertical-align:middle;"><i class="fa fa-phone"></i></h2>
                <h5 style="color:#434343; ">全国免费热线:</h5>
                <h2 >020-85628002</h2>
            </div>
        </div>
    </div>

</div>

<h1 style="text-align: center">
    狼途留言列表页
    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
        <span class="glyphicon glyphicon-plus"></span> 我要留言
    </a>
    <script>
        $(function () {
            $('.btn-success').click(function () {
                $('#messageModal').modal('show')
            })
        })
    </script>
    <!--高级查询--->
    <form class="form-inline" id="searchForm" action="/messageBoard/list" method="post">
        <input type="hidden" name="currentPage" id="currentPage" value="1">
    </form>
</h1>
<#list pageInfo.list as messageBoard>
    <div class="container">
        <div class="box box-widget">
            <div class="box-header with-border">
                <div class="user-block">
                    <img class="img-circle" src="/static/js/adminlte/img/user2-160x160.jpg" alt="User Image">
                    <span class="username"><a href="#">${messageBoard.nickname}</a></span>
                    <span class="description">${messageBoard.createTime?string('yyyy-MM-dd HH:mm')}
                        <span>咨询类型：</span><span>${messageBoard.fatherName}</span>-<span>${messageBoard.systemDictionary.name}</span></span>
                </div>
            </div>
            <div class="box-body">
                <p>${messageBoard.content}</p>
                <span class="pull-right text-muted">
                       <#if messageBoard.replyStatus>
                           已回复<a href="/messageBoard/selectOne?id=${messageBoard.id}">查看详情</a></span
                       </#if>
                <#if !messageBoard.replyStatus>
                        未回复<a href="/messageBoard/selectOne?id=${messageBoard.id}">查看详情</a></span
                </#if>
            </div>
        </div>
    </div>
</#list>
<!--分页-->
<#include "/common/page.ftl" >
<!-- Modal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog " style="max-width: 380px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >请输入留言信息</h4>
            </div>
            <form id="appointmentForm" method="post" action="/messageBoard/insert">
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                        <input  class="form-control" name="nickname" placeholder="请输入您的昵称">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-home"></i></span>
                        <select class="form-control" name="systemDictionary." id="classification">
                            <option value="">请选择业务分类</option>
                            <#list systemDictionary as mb>
                                <option value="${mb.id}">${mb.name}</option>
                            </#list>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                        <select class="form-control" name="systemDictionary.id" id="classificationItem" >
                            <option value="">请选择预约业务</option>
                        </select>
                    </div>
                    <script>
                        $(function () {
                            $('#classification').change(function () {
                                var id = $(this).val();
                                var $select = $('#classificationItem');
                                //清楚数据
                                $select.empty();
                                $select.append('<option value="">请选择预约业务</option>');
                                $.get('/systemDictionary/queryItemById','id='+id,function (data) {
                                    data.forEach(function (ele) {
                                        $select.append('<option value="'+ele.id+'">'+ele.name+'</optiong>')
                                    })
                                })
                            })
                        })
                    </script>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                        <textarea  class="form-control" name="content" placeholder="请输入您的留言内容"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary-full">确定留言</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
