package com.rhyme.utils

object CodeUtils {

    internal fun formatDartFileName(className: String): String {
        val result = mutableListOf<Char>()
        val array = className.toCharArray()
        for (i in 0 until array.size) {
            val c = array[i]
            if (isUpperCase(c) && i != 0) {
                result.add('_')
            }
            result.add(c.toLowerCase())
        }
        return String(result.toCharArray())
    }

    private fun isUpperCase(c: Char): Boolean {
        return c.toInt() in 65..90
    }

    internal fun createPageStatefulWidget(
        className: String,
        providerFileName: String,
        projectName: String,
        providerPath: String,
        fileName: String
    ): String {
        return "import 'package:base_plugin/base_plugin.dart';\n" +
                "import 'package:flutter/material.dart';\n" +
                "import 'package:$projectName/$providerPath/$providerFileName';\n" +
                "import 'package:$projectName/src/router_gen/router_meta.dart';\n" +
                "\n" +
                "@RouterMeta(paramName : \"${generateParamName(fileName)}\")\n" +
                "class ${className}Page extends StatefulWidget {\n" +
                "  @override\n" +
                "  _${className}PageState createState() => _${className}PageState();\n" +
                "}\n" +
                "\n" +
                "class _${className}PageState extends State<${className}Page> with PagePresenter<${className}Page, ${className}Provider>{\n" +
                "\n" +
                "  @override\n" +
                "  Widget get buildWidget => null;\n" +
                "}\n"
    }

    private fun generateParamName(fileName: String): String {
        val upName = fileName.substring(0, fileName.lastIndexOf('.')).split('_').map {
            if (it.length > 1) {
                "${it.first().toUpperCase()}${it.substring(1)}"
            } else {
                it
            }
        }.joinToString("");
        return if (upName.length > 1)
            "${upName.first().toLowerCase()}${upName.substring(1)}"
        else {
            upName
        }
    }


    //创建路由
    internal fun createRouters(
        importList: List<String>, //导入文件列表
        paramsList: List<String>, // 参数列表
        routerRegisterList: List<String> // 路由注册列表
    ): String {
        return "import 'package:r_router/r_router.dart';\n" +
                "import 'package:flutter/material.dart';\n" +
                importList.joinToString("\n") +
                "\n\n" +
                "/// Auto Generated By `RRouter Plugin`." +
                "\n/// [Author]    rhyme_lph" +
                "\n/// [Github]    http://github.com/rhymelph" +
                "\n/// [Email]     rhymelph@gmail.com" +
                "\n/// [QQGroup]   129380453" +
                "\n/// [WeChatSub] Dart客栈" +
                "\nclass RRouterProviders{\n" +
                "  ${paramsList.joinToString("\n  ")}\n" +
                "\n" +
                "  void initRouters() {\n" +
                "    ${routerRegisterList.joinToString("\n    ")}" +
                "  \n" +
                "   }\n" +
                "}"
    }


}