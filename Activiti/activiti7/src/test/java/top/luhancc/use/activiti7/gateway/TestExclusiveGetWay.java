package top.luhancc.use.activiti7.gateway;

import top.luhancc.use.activiti7.BaseTest;

/**
 * 排他网关：当有多个分支条件为true时，选择id小的分支进行，当没有条件为true的分支时，则会抛出异常
 * <p>
 * 直接在bpmn中加入即可，然后在线上设置条件，使用和之前的流程是一样的，在代码上没有特殊的要求
 * 启动一个流程实例--->完成自己的待办任务--->结束流程
 * </p>
 *
 * <p>
 * bpmn文件参考:{@code bpmn/holiday/holiday4.bpmn}
 * </p>
 *
 * @author luHan
 * @create 2021/5/8 13:23
 * @since 1.0.0
 */
public class TestExclusiveGetWay extends BaseTest {
}
