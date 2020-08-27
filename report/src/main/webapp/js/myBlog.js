window.onload = function () {
        layui.use(['form', 'layedit', 'laydate', 'upload', 'table', 'layer'], function () {
            var form = layui.form
                , layer = layui.layer
                , laydate = layui.laydate
                , $ = layui.jquery
                , table = layui.table
                , layer = layui.layer

            //博客列表
            table.render({
                id: 'idTest'
                ,elem: '#myBlog'
                , cellMinWidth: 80
                , url: '/blogs'
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
                    {type: 'radio', fixed: 'left',width:100}
                    // , {field: 'LAY_TABLE_INDEX', title: '序号', sort: true, fixed: 'left'}
                    , {field: 'userName', title: '博主',width:300}
                    , {field: 'content', title: '内容',width:1200}
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo',width:300}
                ]]
            });

            //添加博客
            $(document).on('click', '#addBlog', function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    // closeBtn: 0, //不显示关闭按钮
                    anim: 2,
                    edit: 'text',
                    area: ['800px', '500px'],
                    title: '添加博客',
                    btn: ['确定', '取消'],
                    shadeClose: false,
                    content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">博客内容</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input id="content" name="content" lay-verify="required" ' +
                        '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div></form>',
                    yes: function (index, layero) {
                        var contentVal = $("#content").val();
                        $.ajax({
                            url: '/blogs',
                            type: 'post',
                            data: {
                                content: contentVal,
                                userId:$("#userId").val()
                            },
                            success: function (data) {
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
            });

            //查看博客
            $(document).on('click', '#detailBlog', function () {
                var checkStatus = table.checkStatus('idTest'); //idTest 即为基础参数 id 对应的值
                var data = checkStatus.data[0]; //获取选中行的数据
                if(!data){
                    layer.msg("请选择要查看的博客");
                }
                console.log(data);
                $.ajax({
                    url: '/blogs/'+data.id,
                    type: 'get',
                    success: function (data) {
                        if (data.code == 0) {
                            console.log(data.data);
                            var comments = "";
                            for(var i=0;i < data.data.comments.length;i++){
                                comments +=
                                    '<input id="content" name="content" lay-verify="required" ' +
                                    'disabled  placeholder="请输入" autocomplete="off" class="layui-input"' +
                                    'value='+data.data.comments[i].content+'> ';
                            }
                            $("#commentList").append(comments);
                            // layer.close(index);
                            // setTimeout('window.location.reload()',500);
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                })
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    // closeBtn: 0, //不显示关闭按钮
                    anim: 2,
                    edit: 'text',
                    area: ['800px', '500px'],
                    title: '查看博客',
                    btn: ['关闭'],
                    shadeClose: false,
                    content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">博客内容</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input id="content" name="content" lay-verify="required" ' +
                        '                 disabled  placeholder="请输入" autocomplete="off" class="layui-input"  ' +
                        '   value='+data.content+'>'+
                        '           \n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '   <div class="layui-form-item"><label class="layui-form-label">评论列表</label>' +
                        '       <div id="commentList" class="layui-input-block"></div></div>'+
                        '</div></form>',
                    btn1: function (index, layero) {
                        layer.close(index);
                    },
                });
                form.render();
            });

            //编辑博客
            $(document).on('click', '#editBlog', function () {
                var checkStatus = table.checkStatus('idTest'); //idTest 即为基础参数 id 对应的值
                var data = checkStatus.data[0]; //获取选中行的数据
                if(!data){
                    layer.msg("请选择要修改的博客");
                }
                console.log(data);
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    // closeBtn: 0, //不显示关闭按钮
                    anim: 2,
                    area: ['800px', '500px'],
                    title: '编辑博客',
                    btn: ['确定', '取消'],
                    shadeClose: false,
                    content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">博客内容</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input id="editContent" name="content" lay-verify="required" ' +
                        '                   placeholder="请输入" autocomplete="off" class="layui-input"' +
                        'value='+data.content+'>' +
                        '           \n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div></form>',
                    yes: function (index, layero) {
                        var contentVal = $("#editContent").val();
                        $.ajax({
                            url: '/updateBlog',
                            type: 'post',
                            data: {
                                id:data.id,
                                content:contentVal,
                                userId:$("#userId").val()
                            },
                            success: function (data) {
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
            });

            //评论博客
            $(document).on('click', '#commentBlog', function () {
                var checkStatus = table.checkStatus('idTest'); //idTest 即为基础参数 id 对应的值
                var data = checkStatus.data[0]; //获取选中行的数据
                if(!data){
                    layer.msg("请选择要评论的博客");
                }
                console.log(data);
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    // closeBtn: 0, //不显示关闭按钮
                    anim: 2,
                    area: ['800px', '500px'],
                    title: '评论博客',
                    btn: ['确定', '取消'],
                    shadeClose: false,
                    content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">博客内容</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input id="editContent" name="content" lay-verify="required" ' +
                        '                 disabled  placeholder="请输入" autocomplete="off" class="layui-input"' +
                        'value='+data.content+'>'  +
                        '        </div>\n' +
                        '    </div>\n' +
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">评论内容</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input id="commentContent" name="content" lay-verify="required" ' +
                        '                  placeholder="请输入" autocomplete="off" class="layui-input">' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div></form>',
                    yes: function (index, layero) {
                        var commentContent = $("#commentContent").val();
                        $.ajax({
                            url: '/comments',
                            type: 'post',
                            data: {
                                blogId:data.id,
                                content:commentContent,
                                userId:$("#userId").val()
                            },
                            success: function (data) {
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
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话
                console.log(data);
                if(layEvent === 'del'){ //删除
                    layer.confirm('确定删除该博客？', function (index) {
                        $.ajax({
                            url: '/deleteBlog',
                            type: 'post',
                            data:{
                                id:data.id,
                                userId:$("#userId").val()
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
                } else if(layEvent === 'edit'){ //编辑
                        // $.ajax({
                        //     url: '/updateBlog',
                        //     type: 'post',
                        //     data:{
                        //         id:data.id,
                        //         content:data.content,
                        //         userId:$("#userId").val()
                        //     },
                        //     success: function (data) {
                        //         if(data.code == 0){
                        //             layer.msg(data.msg);
                        //         }else{
                        //             layer.msg(data.msg);
                        //         }
                        //     }
                        // })

                } else if(layEvent === 'comment'){
                    // $.ajax({
                    //     url: '/comments',
                    //     type: 'post',
                    //     data:{
                    //         blogId:data.id,
                    //         content:content,
                    //         userId:$("#userId").val()
                    //     },
                    //     success: function (data) {
                    //         if(data.code == 0){
                    //             layer.msg(data.msg);
                    //             obj.del();
                    //             layer.close(index);
                    //             setTimeout('window.location.reload()',500);
                    //         }else{
                    //             layer.msg(data.msg);
                    //         }
                    //     }
                    // })
                }

            });
        })
    }