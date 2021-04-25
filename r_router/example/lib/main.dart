import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:r_router/r_router.dart';

void main() {
  initRouter();
  runApp(MyApp());
}

void initRouter() {
  RRouter.myRouter.interceptors
      .add(RRouterInterceptorWrapper(onRequest: (settings) {
    if (settings.name == '/other') {
      return settings.copyWith(name: '/five');
    } else {
      return settings;
    }
  }));
  // RRouter.myRouter.interceptors
  //     .add(RRouterInterceptorWrapper(onRequest: (settings) {
  //   if (settings.name == '/two') {
  //     return settings.copyWith(name: '/one');
  //   } else {
  //     return settings;
  //   }
  // }));

  // add new
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      // add new
      onGenerateRoute: RRouter.myRouter.routerGenerate,
      navigatorObservers: [
        RRouter.myRouter.observer,
      ],
      // add new
    );
  }
}
// use navigate
// RRouter.myRouter.navigateTo('/three', arguments: {'pageThree': 'hello world!'});
