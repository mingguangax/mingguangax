<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典明细管理</title>
    <#-- 使用相对当前模板文件的路径 再去找另一个模板文件 -->
    <#include "/common/link.ftl">


</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--定义一个变量  菜单回显-->
    <#assign currentMenu="systemDictionaryItem"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>字典明细管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="row" style="margin:20px">
                    <div class="col-xs-2">
                        <div class="panel panel-default">
                            <div class="panel-heading">字典目录</div>
                            <div class="panel-body">
                                <div class="list-group" id="dic">
                                    <a href="/systemDictionaryItem/list" class="list-group-item">业务大类</a>
                                    <a href="/systemDictionaryItem/list" class="list-group-item">业务小类</a>
                                    <a href="/systemDictionaryItem/list" class="list-group-item">结算类型</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-10">
                        <!--高级查询--->
                        <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list" method="post">
                            <input type="hidden" name="currentPage" id="currentPage" value="1">
                            <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                                <span class="glyphicon glyphicon-plus"></span> 添加
                            </a>
                        </form>
                        <!--编写内容-->
                        <div class="box-body table-responsive no-padding ">
                            <table class="table table-hover table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>字典明细标题</th>
                                        <th>字典明细序号</th>
                                        <th>上级明细</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <a href="#">新车</a>
                                        </td>
                                        <td>1</td>
                                        <td>售卖</td>
                                        <td>
                                            <a href="#" class="btn btn-info btn-xs btn-input"  >
                                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <a href="#">二手车</a>
                                        </td>
                                        <td>2</td>
                                        <td>售卖</td>
                                        <td>
                                            <a href="#" class="btn btn-info btn-xs btn-input"  >
                                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>

                            </table>
                            <#include "/common/page.ftl" >
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>




<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                        <input type="hidden" name="id">
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="name" class="col-sm-3 control-label">字典目录：</label>
                            <div class="col-sm-6">
                                <select class="form-control"  >
                                    <option value="1">业务大类</option>
                                    <option value="2">业务小类</option>
                                    <option value="3">结算类型</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label  class="col-sm-3 control-label">上级明细：</label>
                            <div class="col-sm-6">
                                <select class="form-control"  >
                                    <option value="">无</option>
                                    <option value="1">售卖</option>
                                    <option value="2">保养</option>
                                    <option value="3">修理</option>
                                    <option value="4">美容</option>
                                    <option value="5">配件</option>
                                    <option value="6">订货</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label  class="col-sm-3 control-label">明细标题：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control"
                                       placeholder="请输入字典明细编码">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label class="col-sm-3 control-label">明细序号：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control"
                                       placeholder="请输入字典明细序号">
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
