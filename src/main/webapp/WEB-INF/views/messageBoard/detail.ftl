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
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加留言
                    </h4>
                </div>
                <div class="modal-body">
                    <form type="post" action="/employee/insertMsg">
                        <input type="hidden" name="messageId" value="${messageId}">
                        <input type="text" name="content">
                        <input type="submit" name="提交">
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
<h1 style="text-align: center">
    狼途留言详情页
</h1>
<div class="container">
    <div class="box box-widget">
        <div class="box-header with-border">
            <#list pageInfo.list as messageBoard>
            <div class="user-block">
                <img class="img-circle" src="/static/js/adminlte/img/user2-160x160.jpg" alt="User Image">
                <span class="username"><a href="#">${messageBoard.nickname}</a></span>
                <span class="description">${messageBoard.createTime?string('yyyy-MM-dd HH:mm:ss')}
                    <span>咨询类型：</span><span>${messageBoard.fatherName}</span>-<span>${messageBoard.systemDictionary.name}</span></span>
                <div class="float:right"><a href="javascript:;" class="btn-openmodel">回复</a></div>
            </div>
        </div>
        <script>
            $(function () {

                $('.btn-openmodel').click(function () {
                    $.post('/index/checkSession',null
                        ,function (data) {
                            if (data.success){
                                //有权限

                                $('#myModal').modal('show')
                            }else {
                                alert(data.msg);
                                location.href = '/messageBoard/list'
                            }
                        })
                })
            })
        </script>
        <div class="box-body">
            <p>${messageBoard.content}</p>
            <span class="pull-right text-muted">${refuseSize}条回复</span>
        </div>
        <#list refuse as refuse>
        <div class="box-footer box-comments">
            <div class="box-comment">
                <img class="img-circle img-sm" src="/static/js/adminlte/img/user2-160x160.jpg" alt="User Image">
                <div class="comment-text">
                    <span class="username">${refuse.employee.name}
                        <span class="text-muted pull-right">${refuse.createTime?string('yyyy-MM-dd HH:mm:ss')}</span></span>
                    <p>${refuse.content}</p>
                </div>
            </div>
        </div>
        </#list>
        </#list>
    </div>
</div>
<div class="modal fade" id="checkpasModal" tabindex="-1" role="dialog" aria-labelledby="mySaveModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="mySaveModalLabel">修改密码</h4>
            </div>
            <form class="form-horizontal" id="checkPas" method="post" id="editForm">
                <div class="modal-body">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">旧密码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="oldPas" class="form-control" placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="newPas" class="form-control" placeholder="请输入新密码">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-submit">确认修改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消修改</button>
                </div>
            </form>
            <script>
                $(function () {
                    $('.btn-submit').click(function () {
                        $.post('/checkPassword/check',$('#checkPas').serialize() ,function (data) {
                            if (data.success) {
                                //删除当前会话session;
                                location.href = "/checkPassword/deleteSession"
                            }else {
                                //删除失败
                                alert(data.msg);
                            }
                        })
                    })

                })
            </script>
        </div>
    </div>
</div>


</body>

</html>
