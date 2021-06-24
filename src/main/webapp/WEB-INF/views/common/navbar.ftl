<header class="main-header">
    <a href="../../index2.html" class="logo" style="background-color: #222d32;color: #fff;border-right: none;border-bottom: 1px solid grey;">
        <span class="logo-mini">wolf</span>
        <span class="logo-lg"><b>狼途汽车门店管理平台</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" >
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">
                    <a href="#">
                        <i class="fa fa-envelope-o"></i>
                    </a>
                </li>
                <li class="dropdown tasks-menu">
                    <a href="#" >
                        <i class="fa fa-flag-o"></i>
                    </a>
                </li>
                <li class="dropdown notifications-menu">
                    <a href="#" >
                        <i class="fa fa-bell-o"></i>
                    </a>
                </li>
                <li>
                    <a href="#"  class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i></a>
                    <ul class="dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close" style="min-width: 100px;">
                        <li>
                            <a href="#">
                                <i class="fa fa-cog" ></i>
                                个人设置
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
<#--                            /checkPassword/input-->
                            <a href="#" onclick="openMolde()">
                                <i class=" fa fa-user"></i>
                                修改密码
                            </a>
                        </li>
                        <script>
                            function openMolde() {
                                $('#checkpasModal').modal('show');
                            }
                        </script>


                        <li class="divider"></li>
                        <li>
                            <a href="/logout">
                                <i class="fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Modal -->
</header>
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
