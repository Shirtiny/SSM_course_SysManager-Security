package com.SH.AOP;

import com.SH.ConvertUtils.DateAndString;
import com.SH.Domain.SysLog;
import com.SH.Service.Iservice.IsysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Component
@Aspect
public class sysLogAOP {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IsysLogService sysLogService;

    private Class aClass;
    private Method method;
    private Date visitTime;
    private String visitTimeStr="";
    private String ip="";
    private String url="";
    private String username="";

   //创建一个HashMap，存放封装类与基本类型的KV对，用来将封装类型转为基本数据类型
    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };


    /*
    * 前置通知
    * 生成访问方法时的时间
    *获取操作者的信息
    *获取ip
    *获取访问方法的类、类名、方法、方法名
    *利用方法、类获取requestMapping的valueof[0]，拼接成url
    * 单个方法作为切入点
    * @Before("execution(public String com.SH.Controller.productController.findAll(java.util.Map<java.lang.String,java.lang.Object>))")
    * */
//    全部类型的 com.SH.Controller包下 全部类的全部方法
  @Before("execution(* com.SH.Controller.*.*(..))")//前置通知
    public void BeforeAd(JoinPoint joinPoint) throws NoSuchMethodException ,SecurityException{//异常由Class对象中的getMethod方法产生
      //当前访问时间
      visitTime = new Date();
      //转为年-月-日 时:分:秒字符串
      visitTimeStr = DateAndString.DateToString(visitTime, "yyyy-MM-dd HH:mm:ss");


      //获取操作者信息
      SecurityContext context = SecurityContextHolder.getContext();
      User user = (User) context.getAuthentication().getPrincipal();
      username = user.getUsername();//用户名
      Collection<GrantedAuthority> authorities = user.getAuthorities();//角色

    //获取操作者IP地址
    ip = request.getRemoteAddr();

    //获取访问的类和方法，拼接成url
      //获取切入对象的类
      aClass = joinPoint.getTarget().getClass();



      //★获取方法
      //1. 获取切入对象（方法）的名字
       String  methodName=joinPoint.getSignature().getName();

      //2. 获得方法的参数
      Object[] args = joinPoint.getArgs();



      //3. 判断要获取的方法是否有参数
      Class[] argsClass=null;//参数类型数组
      if (args==null||args.length==0)//没有参数
      {
          //通过方法名获取方法//无参方法获取
          method = aClass.getMethod(methodName,argsClass);//获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型(?)

      }else {//有参数

          //创建一个argsClass数组,长度与参数数组相同
          argsClass=new Class[args.length];
          //循环，获取args数组里每个参数的类，并且装入argsClass数组
          for (int i=0;i<args.length;i++){

              argsClass[i]= args[i].getClass();
              System.out.println("遍历出的参数的类名为："+args[i].getClass().getName());

              if (map.get(args[i].getClass().getName())!=null){//能根据参数的类名在自定义的hashMap中找到对应的基本类型

                  argsClass[i]=map.get(args[i].getClass().getName());//则放入class数组,覆盖掉之前的class数组值，此时通过map将参数类型转为了基本数据类型
                  System.out.println("参数类型转换为："+argsClass[i]);
                  //但如果参数真的是Integer类型怎么办呢？就会报异常。传参应该很少是Integer类型吧。。

              }else {//如果根据参数的类名在自定义的map集合中取不到值，则说明参数是其他类型或者是基本数据类型
                  System.out.println("参数是其他类型，或者是基本类型，保持class不变");
              }



//              System.out.println("参数："+args[i]);
          }

          //打印出参数类型
          System.out.println("最终参数类型："+ Arrays.toString(argsClass));
          //通过方法名+参数类型获取方法//有参方法获取
          method= aClass.getMethod(methodName,argsClass);//有的方法，参数是基本数据类型如int，需要将方法内int参数换成Integer封装类
      }

      //拼接url
      String classURL="";//类路径
      String methodURL="";//类后方法路径
//      class和method都拿到后，就可以拿到requestMapping注解里的值
      if (aClass!=null&&method!=null&&aClass!=sysLogAOP.class){//防止空指针，并且class不为自身（？看有人这么写）
//获取类和方法的RequestMapping对象
      RequestMapping classAnnotation =(RequestMapping) aClass.getAnnotation(RequestMapping.class);//类的RequestMapping注解
      RequestMapping methodAnnotation =(RequestMapping) method.getAnnotation(RequestMapping.class);//方法的RequestMapping注解
     if (classAnnotation!=null){
         classURL=classAnnotation.value()[0];
     }
     if (methodAnnotation!=null){
         methodURL=methodAnnotation.value()[0];
     }
      //.value()值为数组
      url=classURL+methodURL;
      }

      ParameterNameDiscoverer dpnd = new DefaultParameterNameDiscoverer();
      String[] argsNames = dpnd.getParameterNames(method);


      //打印输出，方便测试
      System.out.println("访问时间"+ visitTimeStr);
      System.out.println("用户："+username+"；身份："+authorities+"IP地址："+ip);
      System.out.println("访问的类"+aClass+"；方法"+methodName+"\n；URL："+url);
      System.out.println("参数名："+ Arrays.toString(argsNames));
      System.out.println("参数类型："+ Arrays.toString(argsClass));
      System.out.println("传递参数值："+ Arrays.toString(args));



      //测试

  }


  /*
  * 后置通知
  * 生成方法的完成时间
  * 计算耗时
  * 封装日志数据
  * 调用service处理
  * */
  @After("execution(* com.SH.Controller.*.*(..))")
    public void AfterAd() throws Exception {
      //记录方法完成的时间
      Date completeTime=new Date();
      //转换成指定格式字符串
      String completeTimeStr =DateAndString.DateToString(completeTime,"yyyy-MM-dd HH:mm:ss");
      //计算耗时
      Long usedTime=completeTime.getTime()-visitTime.getTime();


      //封装数据
      SysLog sysLog=new SysLog();
      sysLog.setId(completeTimeStr);
      sysLog.setVisitTime(visitTime);
      sysLog.setVisitTimeStr(visitTimeStr);
      sysLog.setUsername(username);
      sysLog.setIp(ip);
      sysLog.setUrl(url);
      sysLog.setExecutionTime(usedTime);
      sysLog.setMethod(method.getName());

      //存入数据库
      boolean flag = sysLogService.insertOne(sysLog);

      //打印输出，方便测试
      System.out.println("完成时间："+completeTimeStr+"；耗时："+usedTime+"毫秒");
      System.out.println("封装日志："+sysLog);
      System.out.println("将日志，存入数据库结果："+flag);
      System.out.println("完毕-----------------------------------------------------------------\n\n");




    }






}
