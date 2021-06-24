<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>门店管理</title>
    <#include "/common/link.ftl">
<#--    <script>-->
<#--        $(function () {-->
<#--            $('.input-date').datetimepicker({-->
<#--                format:'yyyy-mm-dd', //格式-->
<#--                language:'zh-CN', //中文-->
<#--                autoclose: true,//选择后自动关闭-->
<#--                //showMeridian:true, //是否显示上下午-->
<#--                minView:2,//精确到哪位-->
<#--            });-->
<#--        })-->
<#--    </script>-->
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="business"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>门店编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="box-body" style="margin: 10px" class="form-horizontal" id="editForm" action="/business/saveOrUpdate" method="post"  enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${business.id}" >
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>门店名称：</label>
                                <input type="text" class="form-control" name="name" value="${business.name}"
                                       placeholder="请输入门店名称">
                            </div>
                            <div class="form-group">
                                <label >门店电话：</label>
                                <input type="text" class="form-control" name="tel" value="${business.tel}"
                                       placeholder="请输入门店电话">
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>门店地址：</label>
                                <input type="text" class="form-control" name="address" value="${business.address}"
                                       placeholder="请输入门店地址">
                            </div>
                            <div class="form-group">
                                <label>经营日期：</label>
                                <input type="text" class="form-control input-date" value="${(business.openDate?string('yyyy-MM-dd'))!}"
                                       name="openDate"  placeholder="请输入经营日期"/>
                            </div>
                            <script>
                            </script>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-10">
                            <label>门店介绍：</label>
                            <textarea class="form-control" name="intro" rows="4" placeholder="请输入门店介绍" >${business.intro}</textarea>
                        </div>
                    </div>
					 <div class="row">
                        <div class="form-group col-md-10">
                            <label>经营范围：</label>
                            <textarea class="form-control" rows="4"  placeholder="请输入经营范围"
                                      name="scope">${business.scope}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>法人姓名：</label>
                                <input type="text" class="form-control" name="legalName" value="${business.legalName}"
                                       placeholder="请输入法人姓名">
                            </div>
                            <div class="form-group">
                                <label>法人电话：</label>
                                <input type="text" class="form-control" name="legalTel" value="${business.legalTel}"
                                       placeholder="请输入法人电话">
                            </div>
                            <div class="form-group">
                                <label>上传营业执照：</label>
                                <input type="hidden" name="licenseImg" value="${business.licenseImg}" class="form-control">
                                <a href="${business.licenseImg}" class="btn" target="_blank" >
                                    <i class="fa fa-user"></i> 查看附件
                                </a>
                                <input type="file" class="form-control" name="file" >
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>法人身份证：</label>
                                <input type="text" class="form-control" name="legalIdcard" value="${business.legalIdcard}"
                                       placeholder="请输入法人身份证">
                            </div>
                            <div class="form-group">
                                <label>营业执照号码：</label>
                                <input type="text" class="form-control" name="licenseNumber" value="${business.licenseNumber}"
                                       placeholder="请输入营业执照号码">
                            </div>
                            <div class="form-group">
                                <label >门店性质：</label>
                                <select class="form-control" name="mainStore">
                                    <option value="0" <#if business.mainStore>selected</#if> >分店</option>
                                    <option value="1" <#if business.mainStore>selected</#if> >总店</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                </form>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
</body>
</html>
