window.onload = function () {
    layui.use(['form', 'layedit', 'laydate', 'upload', 'table', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate
            , $ = layui.jquery
            , table = layui.table
            , layer = layui.layer
            , upload = layui.upload;

        //添加学生
        $(document).on('click', '#addStu', function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-demo', //样式类名
                // closeBtn: 0, //不显示关闭按钮
                anim: 2,
                area: ['800px', '500px'],
                title: '添加学生',
                btn: ['确定', '取消'],
                shadeClose: false,
                content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">学号</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="studentId" type="text" name="username" lay-verify="required" lay-reqtext="学号是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">姓名</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input type="text" id="name" name="username" lay-verify="required" lay-reqtext="姓名是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">性别</label>\n' +
                    '        <div class="layui-input-block" id="sex">\n' +
                    '            <input type="radio" name="sex" value="m" title="男" checked="">\n' +
                    '            <input type="radio" name="sex" value="f" title="女">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">年龄</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="age" type="number" name="username" lay-verify="required" lay-reqtext="年龄是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">生日</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="birthday" name="date" id="birthday" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off"\n' +
                    '                   class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div></form>',
                yes: function (index, layero) {
                    var studentId = $("#studentId").val();
                    var name = $("#name").val();
                    var sex = $('#sex input[name="sex"]:checked ').val();
                    var age = $("#age").val();
                    var birthday = $("#birthday").val();
                    $.ajax({
                        url: '/addStudent',
                        type: 'post',
                        data: {
                            "studentId": studentId,
                            "name": name,
                            "sex": sex,
                            "age": age,
                            "birthday": birthday
                        },
                        success: function (data) {
                            console.log(data);
                            if (data.code == 0) {
                                layer.msg(data.msg);
                                layer.close(index);
                                setTimeout('window.location.reload()',500);
                            }else{
                                layer.msg(data.msg);
                            }
                        }
                    })
                },
                btn2: function (index, layero) {
                    layer.close(index);
                },
            });
            form.render();
            //日期
            laydate.render({
                elem: '#birthday'
            });
        });

        //学生列表
        table.render({
            elem: '#stuList'
            , cellMinWidth: 80
            , url: '/listStudent'
            , method: 'get'
            , page: {
                layout: ['prev', 'page', 'next', 'count', 'limit', 'skip']
                , groups: 3
            }
            , skin: 'line' //行边框风格
            , even: true //开启隔行背景
            , size: 'lg' //小尺寸的表格
            , toolbar: '#toolbarDemo'
            , defaultToolbar: ['filter', 'exports']
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                , {field: 'studentId', title: '学号'}
                , {field: 'name', title: '姓名', sort: true}
                , {field: 'sex', title: '性别'}
                , {field: 'age', title: '年龄'}
                , {field: 'birthday', title: '生日', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
        });

        //导入学生
        upload.render({
            elem: '#importStu'
            , url: '/import'
            , accept: 'file'
            , done: function (data) {
                if (data.code == 0) {
                    layer.alert(data.msg);
                    // setTimeout('window.location.reload()',1000);
                }else{
                    layer.msg(data.msg);
                }
            }
        });

        //导入诉讼台账
        upload.render({
            elem: '#importLitigation'
            , url: '/import1'
            , accept: 'file'
            , done: function (data) {
                if (data.code == 0) {
                    layer.msg(data.msg);
                    setTimeout('window.location.reload()',1000);
                }else{
                    layer.msg(data.msg);
                }
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            console.log(data);
            layer.confirm('确定删除该学生？', function (index) {
                $.ajax({
                    url: '/deleteStudent',
                    type: 'get',
                    data:{
                      id:data.id
                    },
                    success: function (data) {
                        if(data.code == 0){
                            layer.msg(data.msg);
                            obj.del();
                            layer.close(index);
                            setTimeout('window.location.reload()',500);
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                })
            });
        });
    });
}