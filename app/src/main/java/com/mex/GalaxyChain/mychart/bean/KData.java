package com.mex.GalaxyChain.mychart.bean;


import com.mex.GalaxyChain.mychart.BaseEntitiy;

import java.io.Serializable;
import java.util.List;

/**
 * @author LSJ
 * @Description
 * @date 2017/4/18 下午5:04
 */
public class KData extends BaseEntitiy implements Serializable {


    /**
     * data : {"TdKxts":[{"id":83048,"close":274.36,"high":274.38,"instID":"Au(T+D)","low":274.35,"open":274.38,"time":"201708031350","type":1,"volume":658,"ovolume":55838,"ctime":1501739417000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739398000,"rose":0.01,"roseF":0.04,"average":274.82,"quoteDate":"20170803","quoteTime":"13:49:58","sequenceNo":103634},{"id":83069,"close":274.38,"high":274.38,"instID":"Au(T+D)","low":274.35,"open":274.36,"time":"201708031355","type":1,"volume":286,"ovolume":56124,"ctime":1501739718000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739699000,"rose":-0.01,"roseF":-0.02,"average":274.81,"quoteDate":"20170803","quoteTime":"13:54:59","sequenceNo":104538},{"id":83086,"close":274.32,"high":274.39,"instID":"Au(T+D)","low":274.3,"open":274.39,"time":"201708031360","type":1,"volume":1404,"ovolume":57528,"ctime":1501740022000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739999000,"rose":0.02,"roseF":0.06,"average":274.8,"quoteDate":"20170803","quoteTime":"13:59:59","sequenceNo":105340},{"id":83103,"close":274.25,"high":274.32,"instID":"Au(T+D)","low":274.23,"open":274.32,"time":"201708031405","type":1,"volume":1008,"ovolume":58536,"ctime":1501740320000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740299000,"rose":0.03,"roseF":0.07,"average":274.79,"quoteDate":"20170803","quoteTime":"14:04:59","sequenceNo":106243},{"id":83129,"close":274.3,"high":274.3,"instID":"Au(T+D)","low":274.22,"open":274.25,"time":"201708031410","type":1,"volume":1306,"ovolume":59842,"ctime":1501740618000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740597000,"rose":-0.02,"roseF":-0.05,"average":274.78,"quoteDate":"20170803","quoteTime":"14:09:57","sequenceNo":107141},{"id":83145,"close":274.26,"high":274.3,"instID":"Au(T+D)","low":274.25,"open":274.3,"time":"201708031415","type":1,"volume":290,"ovolume":60132,"ctime":1501740917000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740893000,"rose":0.01,"roseF":0.04,"average":274.78,"quoteDate":"20170803","quoteTime":"14:14:53","sequenceNo":107930},{"id":83161,"close":274.25,"high":274.27,"instID":"Au(T+D)","low":274.22,"open":274.26,"time":"201708031420","type":1,"volume":880,"ovolume":61012,"ctime":1501741215000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501741192000,"rose":0,"roseF":0.01,"average":274.77,"quoteDate":"20170803","quoteTime":"14:19:52","sequenceNo":108787},{"id":83181,"close":274.25,"high":274.25,"instID":"Au(T+D)","low":274.21,"open":274.22,"time":"201708031430","type":1,"volume":868,"ovolume":61880,"ctime":1501741817000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501741799000,"rose":0,"roseF":0,"average":274.77,"quoteDate":"20170803","quoteTime":"14:29:59","sequenceNo":110461},{"id":83210,"close":274.26,"high":274.28,"instID":"Au(T+D)","low":274.25,"open":274.25,"time":"201708031435","type":1,"volume":692,"ovolume":62572,"ctime":1501742118000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501742099000,"rose":0,"roseF":-0.01,"average":274.76,"quoteDate":"20170803","quoteTime":"14:34:59","sequenceNo":111347},{"id":83233,"close":274.26,"high":274.29,"instID":"Au(T+D)","low":274.26,"open":274.27,"time":"201708031440","type":1,"volume":138,"ovolume":62710,"ctime":1501742238000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501742230000,"rose":0,"roseF":0,"average":274.76,"quoteDate":"20170803","quoteTime":"14:37:10","sequenceNo":111668},{"id":80986,"close":274.94,"high":275.12,"instID":"Au(T+D)","low":274.91,"open":274.97,"time":"201708032005","type":1,"volume":198,"ovolume":198,"ctime":1501675576000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501761895000,"rose":-0.03,"roseF":-0.09,"average":274.96,"quoteDate":"20170803","quoteTime":"20:04:55","sequenceNo":3027},{"id":81019,"close":274.99,"high":275.03,"instID":"Au(T+D)","low":274.91,"open":274.94,"time":"201708032010","type":1,"volume":228,"ovolume":426,"ctime":1501675880000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762199000,"rose":-0.02,"roseF":-0.05,"average":274.97,"ququoteDate":"20170803","quoteTime":"20:09:59","sequenceNo":4274},{"id":81037,"close":275.07,"high":275.07,"instID":"Au(T+D)","low":274.91,"open":274.99,"time":"201708032015","type":1,"volume":326,"ovolume":752,"ctime":1501676236000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762499000,"rose":-0.03,"roseF":-0.08,"average":274.96,"quoteDate":"20170803","quoteTime":"20:14:59","sequenceNo":5509},{"id":81055,"close":275.22,"high":275.44,"instID":"Au(T+D)","low":274.86,"open":275.07,"time":"201708032020","type":1,"volume":2004,"ovolume":2756,"ctime":1501677018000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762799000,"rose":-0.05,"roseF":-0.15,"average":275.08,"quoteDate":"20170803","quoteTime":"20:19:59","sequenceNo":6931},{"id":81076,"close":275.12,"high":275.3,"instID":"Au(T+D)","low":275.1,"open":275.22,"time":"201708032030","type":1,"volume":972,"ovolume":3728,"ctime":1501677861000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763392000,"rose":0.04,"roseF":0.1,"average":275.11,"quoteDate":"20170803","quoteTime":"20:29:52","sequenceNo":9370},{"id":81112,"close":275.26,"high":275.35,"instID":"Au(T+D)","low":275.11,"open":275.11,"time":"201708032035","type":1,"volume":368,"ovolume":4096,"ctime":1501678396000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763699000,"rose":-0.05,"roseF":-0.14,"average":275.12,"quoteDate":"20170803","quoteTime":"20:34:59","sequenceNo":10634},{"id":81132,"close":275,"high":275.25,"instID":"Au(T+D)","low":274.91,"open":275.25,"time":"201708032040","type":1,"volume":614,"ovolume":4710,"ctime":1501678762000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763994000,"rose":0.09,"roseF":0.26,"average":275.11,"quoteDate":"20170803","quoteTime":"20:39:54","sequenceNo":11785},{"id":81154,"close":274.93,"high":275,"instID":"Au(T+D)","low":274.86,"open":275,"time":"201708032045","type":1,"volume":418,"ovolume":5128,"ctime":1501679180000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764297000,"rose":0.03,"roseF":0.07,"average":275.1,"quoteDate":"20170803","quoteTime":"20:44:57","sequenceNo":13008},{"id":81174,"close":274.63,"high":274.88,"instID":"Au(T+D)","low":274.55,"open":274.88,"time":"201708032050","type":1,"volume":1156,"ovolume":6284,"ctime":1501679897000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764599000,"rose":0.11,"roseF":0.3,"average":275.02,"quoteDate":"20170803","quoteTime":"20:49:59","sequenceNo":14404},{"id":81189,"close":274.67,"high":274.9,"instID":"Au(T+D)","low":274.5,"open":274.63,"time":"201708032055","type":1,"volume":1040,"ovolume":7324,"ctime":1501680620000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764898000,"rose":-0.01,"roseF":-0.04,"average":274.96,"quoteDate":"20170803","quoteTime":"20:54:58","sequenceNo":15752},{"id":81207,"close":274.72,"high":274.75,"instID":"Au(T+D)","low":274.67,"open":274.67,"time":"201708032060","type":1,"volume":1062,"ovolume":8386,"ctime":1501681217000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501765195000,"rose":-0.02,"roseF":-0.05,"average":274.93,"quoteDate":"20170803","quoteTime":"20:59:55","sequenceNo":17057},{"id":81225,"close":274.77,"high":274.85,"instID":"Au(T+D)","low":274.7,"open":274.72,"time":"201708032105","type":1,"volume":646,"ovolume":9032,"ctime":1501681817000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501765499000,"rose":-0.02,"roseF":-0.05,"average":274.92,"quoteDate":"20170803","quoteTime":"21:04:59","sequenceNo":18415},{"id":81252,"close":274.87,"high":274.89,"instID":"Au(T+D)","low":274.77,"open":274.77,"time":"201708032110","type":1,"volume":332,"ovolume":9364,"ctime":1501682356000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501765798000,"rose":-0.04,"roseF":-0.1,"average":274.91,"quoteDate":"20170803","quoteTime":"21:09:58","sequenceNo":19688},{"id":81270,"close":275,"high":275.05,"instID":"Au(T+D)","low":274.86,"open":274.87,"time":"201708032115","type":1,"volume":298,"ovolume":9662,"ctime":1501682837000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766099000,"rose":-0.05,"roseF":-0.13,"average":274.91,"quoteDate":"20170803","quoteTime":"21:14:59","sequenceNo":20971},{"id":81288,"close":275.22,"high":275.25,"instID":"Au(T+D)","low":274.94,"open":275,"time":"201708032120","type":1,"volume":500,"ovolume":10162,"ctime":1501683319000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766399000,"rose":-0.08,"roseF":-0.22,"average":274.92,"quoteDate":"20170803","quoteTime":"21:19:59","sequenceNo":22218},{"id":81309,"close":275.17,"high":275.36,"instID":"Au(T+D)","low":275.16,"open":275.22,"time":"201708032130","type":1,"volume":1026,"ovolume":11188,"ctime":1501684278000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766998000,"rose":0.02,"roseF":0.05,"average":274.95,"quoteDate":"20170803","quoteTime":"21:29:58","sequenceNo":24683},{"id":81342,"close":275.2,"high":275.29,"instID":"Au(T+D)","low":275.16,"open":275.17,"time":"201708032135","type":1,"volume":664,"ovolume":11852,"ctime":1501684637000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767296000,"rose":-0.01,"roseF":-0.03,"average":274.97,"quoteDate":"20170803","quoteTime":"21:34:56","sequenceNo":25822},{"id":81366,"close":275.19,"high":275.27,"instID":"Au(T+D)","low":275.17,"open":275.2,"time":"201708032140","type":1,"volume":268,"ovolume":12120,"ctime":1501684936000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767599000,"rose":0,"roseF":0.01,"average":274.97,"quoteDate":"20170803","quoteTime":"21:39:59","sequenceNo":26912},{"id":81383,"close":275.5,"high":275.55,"instID":"Au(T+D)","low":275.19,"open":275.19,"time":"201708032145","type":1,"volume":1600,"ovolume":13720,"ctime":1501685481000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767899000,"rose":-0.11,"roseF":-0.31,"average":275.03,"quoteDate":"20170803","quoteTime":"21:44:59","sequenceNo":28148},{"id":81401,"close":275.36,"high":275.6,"instID":"Au(T+D)","low":275.35,"open":275.45,"time":"201708032150","type":1,"volume":1064,"ovolume":14784,"ctime":1501686083000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768199000,"rose":0.05,"roseF":0.14,"average":275.06,"quoteDate":"20170803","quoteTime":"21:49:59","sequenceNo":29463},{"id":81426,"close":275.38,"high":275.45,"instID":"Au(T+D)","low":275.36,"open":275.36,"time":"201708032155","type":1,"volume":250,"ovolume":15034,"ctime":1501686439000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768497000,"rose":-0.01,"roseF":-0.02,"average":275.06,"quoteDate":"20170803","quoteTime":"21:54:57","sequenceNo":30612},{"id":81439,"close":275.41,"high":275.46,"instID":"Au(T+D)","low":275.36,"open":275.38,"time":"201708032160","type":1,"volume":332,"ovolume":15366,"ctime":1501686737000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768799000,"rose":-0.01,"roseF":-0.03,"average":275.07,"quoteDate":"20170803","quoteTime":"21:59:59","sequenceNo":31711},{"id":81456,"close":275.33,"high":275.46,"instID":"Au(T+D)","low":275.33,"open":275.41,"time":"201708032205","type":1,"volume":312,"ovolume":15678,"ctime":1501687041000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769090000,"rose":0.03,"roseF":0.08,"average":275.08,"quoteDate":"20170803","quoteTime":"22:04:50","sequenceNo":32789},{"id":81487,"close":275.28,"high":275.39,"instID":"Au(T+D)","low":275.28,"open":275.33,"time":"201708032210","type":1,"volume":406,"ovolume":16084,"ctime":1501687342000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769396000,"rose":0.02,"roseF":0.05,"average":275.08,"quoteDate":"20170803","quoteTime":"22:09:56","sequenceNo":33875},{"id":81498,"close":275.4,"high":275.44,"instID":"Au(T+D)","low":275.16,"open":275.21,"time":"201708032215","type":1,"volume":300,"ovolume":16384,"ctime":1501687703000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769687000,"rose":-0.04,"roseF":-0.12,"average":275.09,"quoteDate":"20170803","quoteTime":"22:14:47","sequenceNo":34965},{"id":81516,"close":275.36,"high":275.46,"instID":"Au(T+D)","low":275.35,"open":275.41,"time":"201708032220","type":1,"volume":240,"ovolume":16624,"ctime":1501688175000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769996000,"rose":0.01,"roseF":0.04,"average":275.09,"quoteDate":"20170803","quoteTime":"22:19:56","sequenceNo":36204},{"id":81536,"close":275.37,"high":275.48,"instID":"Au(T+D)","low":275.3,"open":275.4,"time":"201708032230","type":1,"volume":588,"ovolume":17212,"ctime":1501688778000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501770599000,"rose":0,"roseF":-0.01,"average":275.1,"quoteDate":"20170803","quoteTime":"22:29:59","sequenceNo":38338},{"id":81566,"close":275.4,"high":275.4,"instID":"Au(T+D)","low":275.3,"open":275.35,"time":"201708032235","type":1,"volume":350,"ovolume":17562,"ctime":1501689017000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501770898000,"rose":-0.01,"roseF":-0.03,"average":275.11,"quoteDate":"20170803","quoteTime":"22:34:58","sequenceNo":39324},{"id":81588,"close":275.41,"high":275.42,"instID":"Au(T+D)","low":275.4,"open":275.4,"time":"201708032240","type":1,"volume":248,"ovolume":17810,"ctime":1501689198000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771193000,"rose":0,"roseF":-0.01,"average":275.11,"quoteDate":"20170803","quoteTime":"22:39:53","sequenceNo":40189},{"id":81603,"close":275.5,"high":275.5,"instID":"Au(T+D)","low":275.41,"open":275.41,"time":"201708032245","type":1,"volume":624,"ovolume":18434,"ctime":1501689386000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771495000,"rose":-0.03,"roseF":-0.09,"average":275.13,"quoteDate":"20170803","quoteTime":"22:44:55","sequenceNo":41071},{"id":81624,"close":275.48,"high":275.54,"instID":"Au(T+D)","low":275.45,"open":275.5,"time":"201708032250","type":1,"volume":450,"ovolume":18884,"ctime":1501689618000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771798000,"rose":0.01,"roseF":0.02,"average":275.13,"quoteDate":"20170803","quoteTime":"22:49:58","sequenceNo":41934},{"id":81638,"close":275.38,"high":275.48,"instID":"Au(T+D)","low":275.38,"open":275.48,"time":"201708032255","type":1,"volume":96,"ovolume":18980,"ctime":1501689746000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501772099000,"rose":0.04,"roseF":0.1,"average":275.14,"quoteDate":"20170803","quoteTime":"22:54:59","sequenceNo":42674},{"id":81650,"close":275.54,"high":275.55,"instID":"Au(T+D)","low":275.38,"open":275.38,"time":"201708032260","type":1,"volume":654,"ovolume":19634,"ctime":1501689980000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501772399000,"rose":-0.06,"roseF":-0.16,"average":275.15,"quoteDate":"20170803","quoteTime":"22:59:59","sequenceNo":43540},{"id":81666,"close":275.54,"high":275.54,"instID":"Au(T+D)","low":275.49,"open":275.54,"time":"201708032305","type":1,"volume":244,"ovolume":19878,"ctime":1501690164000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501772694000,"rose":0,"roseF":0,"average":275.15,"quoteDate":"20170803","quoteTime":"23:04:54","sequenceNo":44410},{"id":81694,"close":275.45,"high":275.54,"instID":"Au(T+D)","low":275.44,"open":275.53,"time":"201708032310","type":1,"volume":284,"ovolume":20162,"ctime":1501690518000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501772991000,"rose":0.03,"roseF":0.09,"average":275.16,"quoteDate":"20170803","quoteTime":"23:09:51","sequenceNo":45514},{"id":81708,"close":275.38,"high":275.51,"instID":"Au(T+D)","low":275.38,"open":275.45,"time":"201708032315","type":1,"volume":412,"ovolume":20574,"ctime":1501690878000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501773280000,"rose":0.03,"roseF":0.07,"average":275.16,"quoteDate":"20170803","quoteTime":"23:14:40","sequenceNo":46674},{"id":81724,"close":275.42,"high":275.44,"instID":"Au(T+D)","low":275.36,"open":275.36,"time":"201708032320","type":1,"volume":138,"ovolume":20712,"ctime":1501691175000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501773597000,"rose":-0.01,"roseF":-0.04,"average":275.17,"quoteDate":"20170803","quoteTime":"23:19:57","sequenceNo":47748},{"id":81743,"close":275.65,"high":275.66,"instID":"Au(T+D)","low":275.4,"open":275.42,"time":"201708032330","type":1,"volume":898,"ovolume":21610,"ctime":1501691720000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774199000,"rose":-0.08,"roseF":-0.23,"average":275.18,"quoteDate":"20170803","quoteTime":"23:29:59","sequenceNo":49825},{"id":81781,"close":275.65,"high":275.66,"instID":"Au(T+D)","low":275.5,"open":275.65,"time":"201708032335","type":1,"volume":392,"ovolume":22002,"ctime":1501692195000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774499000,"rose":0,"roseF":0,"average":275.19,"quoteDate":"20170803","quoteTime":"23:34:59","sequenceNo":51059},{"id":81797,"close":275.57,"high":275.65,"instID":"Au(T+D)","low":275.55,"open":275.6,"time":"201708032340","type":1,"volume":132,"ovolume":22134,"ctime":1501692439000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774798000,"rose":0.03,"roseF":0.08,"average":275.19,"quoteDate":"20170803","quoteTime":"23:39:58","sequenceNo":52170},{"id":81817,"close":275.49,"high":275.58,"instID":"Au(T+D)","low":275.49,"open":275.57,"time":"201708032345","type":1,"volume":94,"ovolume":22228,"ctime":1501692679000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775092000,"rose":0.03,"roseF":0.08,"average":275.19,"quoteDate":"20170803","quoteTime":"23:44:52","sequenceNo":53126},{"id":81830,"close":275.42,"high":275.52,"instID":"Au(T+D)","low":275.41,"open":275.49,"time":"201708032350","type":1,"volume":106,"ovolume":22334,"ctime":1501692857000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775395000,"rose":0.03,"roseF":0.07,"average":275.19,"quoteDate":"20170803","quoteTime":"23:49:55","sequenceNo":54042},{"id":81849,"close":275.52,"high":275.52,"instID":"Au(T+D)","low":275.41,"open":275.42,"time":"201708032355","type":1,"volume":60,"ovolume":22394,"ctime":1501692983000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775676000,"rose":-0.04,"roseF":-0.1,"average":275.19,"quoteDate":"20170803","quoteTime":"23:54:36","sequenceNo":54805},{"id":81863,"close":275.55,"high":275.55,"instID":"Au(T+D)","low":275.45,"open":275.5,"time":"201708032360","type":1,"volume":112,"ovolume":22506,"ctime":1501693164000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775994000,"rose":-0.01,"roseF":-0.03,"average":275.2,"quoteDate":"20170803","quoteTime":"23:59:54","sequenceNo":55583}],"vom":2004}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * TdKxts : [{"id":83048,"close":274.36,"high":274.38,"instID":"Au(T+D)","low":274.35,"open":274.38,"time":"201708031350","type":1,"volume":658,"ovolume":55838,"ctime":1501739417000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739398000,"rose":0.01,"roseF":0.04,"average":274.82,"quoteDate":"20170803","quoteTime":"13:49:58","sequenceNo":103634},{"id":83069,"close":274.38,"high":274.38,"instID":"Au(T+D)","low":274.35,"open":274.36,"time":"201708031355","type":1,"volume":286,"ovolume":56124,"ctime":1501739718000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739699000,"rose":-0.01,"roseF":-0.02,"average":274.81,"quoteDate":"20170803","quoteTime":"13:54:59","sequenceNo":104538},{"id":83086,"close":274.32,"high":274.39,"instID":"Au(T+D)","low":274.3,"open":274.39,"time":"201708031360","type":1,"volume":1404,"ovolume":57528,"ctime":1501740022000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"13","utime":1501739999000,"rose":0.02,"roseF":0.06,"average":274.8,"quoteDate":"20170803","quoteTime":"13:59:59","sequenceNo":105340},{"id":83103,"close":274.25,"high":274.32,"instID":"Au(T+D)","low":274.23,"open":274.32,"time":"201708031405","type":1,"volume":1008,"ovolume":58536,"ctime":1501740320000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740299000,"rose":0.03,"roseF":0.07,"average":274.79,"quoteDate":"20170803","quoteTime":"14:04:59","sequenceNo":106243},{"id":83129,"close":274.3,"high":274.3,"instID":"Au(T+D)","low":274.22,"open":274.25,"time":"201708031410","type":1,"volume":1306,"ovolume":59842,"ctime":1501740618000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740597000,"rose":-0.02,"roseF":-0.05,"average":274.78,"quoteDate":"20170803","quoteTime":"14:09:57","sequenceNo":107141},{"id":83145,"close":274.26,"high":274.3,"instID":"Au(T+D)","low":274.25,"open":274.3,"time":"201708031415","type":1,"volume":290,"ovolume":60132,"ctime":1501740917000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501740893000,"rose":0.01,"roseF":0.04,"average":274.78,"quoteDate":"20170803","quoteTime":"14:14:53","sequenceNo":107930},{"id":83161,"close":274.25,"high":274.27,"instID":"Au(T+D)","low":274.22,"open":274.26,"time":"201708031420","type":1,"volume":880,"ovolume":61012,"ctime":1501741215000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501741192000,"rose":0,"roseF":0.01,"average":274.77,"quoteDate":"20170803","quoteTime":"14:19:52","sequenceNo":108787},{"id":83181,"close":274.25,"high":274.25,"instID":"Au(T+D)","low":274.21,"open":274.22,"time":"201708031430","type":1,"volume":868,"ovolume":61880,"ctime":1501741817000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501741799000,"rose":0,"roseF":0,"average":274.77,"quoteDate":"20170803","quoteTime":"14:29:59","sequenceNo":110461},{"id":83210,"close":274.26,"high":274.28,"instID":"Au(T+D)","low":274.25,"open":274.25,"time":"201708031435","type":1,"volume":692,"ovolume":62572,"ctime":1501742118000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501742099000,"rose":0,"roseF":-0.01,"average":274.76,"quoteDate":"20170803","quoteTime":"14:34:59","sequenceNo":111347},{"id":83233,"close":274.26,"high":274.29,"instID":"Au(T+D)","low":274.26,"open":274.27,"time":"201708031440","type":1,"volume":138,"ovolume":62710,"ctime":1501742238000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"14","utime":1501742230000,"rose":0,"roseF":0,"average":274.76,"quoteDate":"20170803","quoteTime":"14:37:10","sequenceNo":111668},{"id":80986,"close":274.94,"high":275.12,"instID":"Au(T+D)","low":274.91,"open":274.97,"time":"201708032005","type":1,"volume":198,"ovolume":198,"ctime":1501675576000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501761895000,"rose":-0.03,"roseF":-0.09,"average":274.96,"quoteDate":"20170803","quoteTime":"20:04:55","sequenceNo":3027},{"id":81019,"close":274.99,"high":275.03,"instID":"Au(T+D)","low":274.91,"open":274.94,"time":"201708032010","type":1,"volume":228,"ovolume":426,"ctime":1501675880000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762199000,"rose":-0.02,"roseF":-0.05,"average":274.97,"quoteDate":"20170803","quoteTime":"20:09:59","sequenceNo":4274},{"id":81037,"close":275.07,"high":275.07,"instID":"Au(T+D)","low":274.91,"open":274.99,"time":"201708032015","type":1,"volume":326,"ovolume":752,"ctime":1501676236000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762499000,"rose":-0.03,"roseF":-0.08,"average":274.96,"quoteDate":"20170803","quoteTime":"20:14:59","sequenceNo":5509},{"id":81055,"close":275.22,"high":275.44,"instID":"Au(T+D)","low":274.86,"open":275.07,"time":"201708032020","type":1,"volume":2004,"ovolume":2756,"ctime":1501677018000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501762799000,"rose":-0.05,"roseF":-0.15,"average":275.08,"quoteDate":"20170803","quoteTime":"20:19:59","sequenceNo":6931},{"id":81076,"close":275.12,"high":275.3,"instID":"Au(T+D)","low":275.1,"open":275.22,"time":"201708032030","type":1,"volume":972,"ovolume":3728,"ctime":1501677861000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763392000,"rose":0.04,"roseF":0.1,"average":275.11,"quoteDate":"20170803","quoteTime":"20:29:52","sequenceNo":9370},{"id":81112,"close":275.26,"high":275.35,"instID":"Au(T+D)","low":275.11,"open":275.11,"time":"201708032035","type":1,"volume":368,"ovolume":4096,"ctime":1501678396000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763699000,"rose":-0.05,"roseF":-0.14,"average":275.12,"quoteDate":"20170803","quoteTime":"20:34:59","sequenceNo":10634},{"id":81132,"close":275,"high":275.25,"instID":"Au(T+D)","low":274.91,"open":275.25,"time":"201708032040","type":1,"volume":614,"ovolume":4710,"ctime":1501678762000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501763994000,"rose":0.09,"roseF":0.26,"average":275.11,"quoteDate":"20170803","quoteTime":"20:39:54","sequenceNo":11785},{"id":81154,"close":274.93,"high":275,"instID":"Au(T+D)","low":274.86,"open":275,"time":"201708032045","type":1,"volume":418,"ovolume":5128,"ctime":1501679180000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764297000,"rose":0.03,"roseF":0.07,"average":275.1,"quoteDate":"20170803","quoteTime":"20:44:57","sequenceNo":13008},{"id":81174,"close":274.63,"high":274.88,"instID":"Au(T+D)","low":274.55,"open":274.88,"time":"201708032050","type":1,"volume":1156,"ovolume":6284,"ctime":1501679897000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764599000,"rose":0.11,"roseF":0.3,"average":275.02,"quoteDate":"20170803","quoteTime":"20:49:59","sequenceNo":14404},{"id":81189,"close":274.67,"high":274.9,"instID":"Au(T+D)","low":274.5,"open":274.63,"time":"201708032055","type":1,"volume":1040,"ovolume":7324,"ctime":1501680620000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501764898000,"rose":-0.01,"roseF":-0.04,"average":274.96,"quoteDate":"20170803","quoteTime":"20:54:58","sequenceNo":15752},{"id":81207,"close":274.72,"high":274.75,"instID":"Au(T+D)","low":274.67,"open":274.67,"time":"201708032060","type":1,"volume":1062,"ovolume":8386,"ctime":1501681217000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"20","utime":1501765195000,"rose":-0.02,"roseF":-0.05,"average":274.93,"quoteDate":"20170803","quoteTime":"20:59:55","sequenceNo":17057},{"id":81225,"close":274.77,"high":274.85,"instID":"Au(T+D)","low":274.7,"open":274.72,"time":"201708032105","type":1,"volume":646,"ovolume":9032,"ctime":1501681817000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501765499000,"rose":-0.02,"roseF":-0.05,"average":274.92,"quoteDate":"20170803","quoteTime":"21:04:59","sequenceNo":18415},{"id":81252,"close":274.87,"high":274.89,"instID":"Au(T+D)","low":274.77,"open":274.77,"time":"201708032110","type":1,"volume":332,"ovolume":9364,"ctime":1501682356000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501765798000,"rose":-0.04,"roseF":-0.1,"average":274.91,"quoteDate":"20170803","quoteTime":"21:09:58","sequenceNo":19688},{"id":81270,"close":275,"high":275.05,"instID":"Au(T+D)","low":274.86,"open":274.87,"time":"201708032115","type":1,"volume":298,"ovolume":9662,"ctime":1501682837000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766099000,"rose":-0.05,"roseF":-0.13,"average":274.91,"quoteDate":"20170803","quoteTime":"21:14:59","sequenceNo":20971},{"id":81288,"close":275.22,"high":275.25,"instID":"Au(T+D)","low":274.94,"open":275,"time":"201708032120","type":1,"volume":500,"ovolume":10162,"ctime":1501683319000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766399000,"rose":-0.08,"roseF":-0.22,"average":274.92,"quoteDate":"20170803","quoteTime":"21:19:59","sequenceNo":22218},{"id":81309,"close":275.17,"high":275.36,"instID":"Au(T+D)","low":275.16,"open":275.22,"time":"201708032130","type":1,"volume":1026,"ovolume":11188,"ctime":1501684278000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501766998000,"rose":0.02,"roseF":0.05,"average":274.95,"quoteDate":"20170803","quoteTime":"21:29:58","sequenceNo":24683},{"id":81342,"close":275.2,"high":275.29,"instID":"Au(T+D)","low":275.16,"open":275.17,"time":"201708032135","type":1,"volume":664,"ovolume":11852,"ctime":1501684637000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767296000,"rose":-0.01,"roseF":-0.03,"average":274.97,"quoteDate":"20170803","quoteTime":"21:34:56","sequenceNo":25822},{"id":81366,"close":275.19,"high":275.27,"instID":"Au(T+D)","low":275.17,"open":275.2,"time":"201708032140","type":1,"volume":268,"ovolume":12120,"ctime":1501684936000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767599000,"rose":0,"roseF":0.01,"average":274.97,"quoteDate":"20170803","quoteTime":"21:39:59","sequenceNo":26912},{"id":81383,"close":275.5,"high":275.55,"instID":"Au(T+D)","low":275.19,"open":275.19,"time":"201708032145","type":1,"volume":1600,"ovolume":13720,"ctime":1501685481000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501767899000,"rose":-0.11,"roseF":-0.31,"average":275.03,"quoteDate":"20170803","quoteTime":"21:44:59","sequenceNo":28148},{"id":81401,"close":275.36,"high":275.6,"instID":"Au(T+D)","low":275.35,"open":275.45,"time":"201708032150","type":1,"volume":1064,"ovolume":14784,"ctime":1501686083000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768199000,"rose":0.05,"roseF":0.14,"average":275.06,"quoteDate":"20170803","quoteTime":"21:49:59","sequenceNo":29463},{"id":81426,"close":275.38,"high":275.45,"instID":"Au(T+D)","low":275.36,"open":275.36,"time":"201708032155","type":1,"volume":250,"ovolume":15034,"ctime":1501686439000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768497000,"rose":-0.01,"roseF":-0.02,"average":275.06,"quoteDate":"20170803","quoteTime":"21:54:57","sequenceNo":30612},{"id":81439,"close":275.41,"high":275.46,"instID":"Au(T+D)","low":275.36,"open":275.38,"time":"201708032160","type":1,"volume":332,"ovolume":15366,"ctime":1501686737000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"21","utime":1501768799000,"rose":-0.01,"roseF":-0.03,"average":275.07,"quoteDate":"20170803","quoteTime":"21:59:59","sequenceNo":31711},{"id":81456,"close":275.33,"high":275.46,"instID":"Au(T+D)","low":275.33,"open":275.41,"time":"201708032205","type":1,"volume":312,"ovolume":15678,"ctime":1501687041000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769090000,"rose":0.03,"roseF":0.08,"average":275.08,"quoteDate":"20170803","quoteTime":"22:04:50","sequenceNo":32789},{"id":81487,"close":275.28,"high":275.39,"instID":"Au(T+D)","low":275.28,"open":275.33,"time":"201708032210","type":1,"volume":406,"ovolume":16084,"ctime":1501687342000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769396000,"rose":0.02,"roseF":0.05,"average":275.08,"quoteDate":"20170803","quoteTime":"22:09:56","sequenceNo":33875},{"id":81498,"close":275.4,"high":275.44,"instID":"Au(T+D)","low":275.16,"open":275.21,"time":"201708032215","type":1,"volume":300,"ovolume":16384,"ctime":1501687703000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769687000,"rose":-0.04,"roseF":-0.12,"average":275.09,"quoteDate":"20170803","quoteTime":"22:14:47","sequenceNo":34965},{"id":81516,"close":275.36,"high":275.46,"instID":"Au(T+D)","low":275.35,"open":275.41,"time":"201708032220","type":1,"volume":240,"ovolume":16624,"ctime":1501688175000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501769996000,"rose":0.01,"roseF":0.04,"average":275.09,"quoteDate":"20170803","quoteTime":"22:19:56","sequenceNo":36204},{"id":81536,"close":275.37,"high":275.48,"instID":"Au(T+D)","low":275.3,"open":275.4,"time":"201708032230","type":1,"volume":588,"ovolume":17212,"ctime":1501688778000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501770599000,"rose":0,"roseF":-0.01,"average":275.1,"quoteDate":"20170803","quoteTime":"22:29:59","sequenceNo":38338},{"id":81566,"close":275.4,"high":275.4,"instID":"Au(T+D)","low":275.3,"open":275.35,"time":"201708032235","type":1,"volume":350,"ovolume":17562,"ctime":1501689017000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501770898000,"rose":-0.01,"roseF":-0.03,"average":275.11,"quoteDate":"20170803","quoteTime":"22:34:58","sequenceNo":39324},{"id":81588,"close":275.41,"high":275.42,"instID":"Au(T+D)","low":275.4,"open":275.4,"time":"201708032240","type":1,"volume":248,"ovolume":17810,"ctime":1501689198000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771193000,"rose":0,"roseF":-0.01,"average":275.11,"quoteDate":"20170803","quoteTime":"22:39:53","sequenceNo":40189},{"id":81603,"close":275.5,"high":275.5,"instID":"Au(T+D)","low":275.41,"open":275.41,"time":"201708032245","type":1,"volume":624,"ovolume":18434,"ctime":1501689386000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771495000,"rose":-0.03,"roseF":-0.09,"average":275.13,"quoteDate":"20170803","quoteTime":"22:44:55","sequenceNo":41071},{"id":81624,"close":275.48,"high":275.54,"instID":"Au(T+D)","low":275.45,"open":275.5,"time":"201708032250","type":1,"volume":450,"ovolume":18884,"ctime":1501689618000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501771798000,"rose":0.01,"roseF":0.02,"average":275.13,"quoteDate":"20170803","quoteTime":"22:49:58","sequenceNo":41934},{"id":81638,"close":275.38,"high":275.48,"instID":"Au(T+D)","low":275.38,"open":275.48,"time":"201708032255","type":1,"volume":96,"ovolume":18980,"ctime":1501689746000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501772099000,"rose":0.04,"roseF":0.1,"average":275.14,"quoteDate":"20170803","quoteTime":"22:54:59","sequenceNo":42674},{"id":81650,"close":275.54,"high":275.55,"instID":"Au(T+D)","low":275.38,"open":275.38,"time":"201708032260","type":1,"volume":654,"ovolume":19634,"ctime":1501689980000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"22","utime":1501772399000,"rose":-0.06,"roseF":-0.16,"average":275.15,"quoteDate":"20170803","quoteTime":"22:59:59","sequenceNo":43540},{"id":81666,"close":275.54,"high":275.54,"instID":"Au(T+D)","low":275.49,"open":275.54,"time":"201708032305","type":1,"volume":244,"ovolume":19878,"ctime":1501690164000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501772694000,"rose":0,"roseF":0,"average":275.15,"quoteDate":"20170803","quoteTime":"23:04:54","sequenceNo":44410},{"id":81694,"close":275.45,"high":275.54,"instID":"Au(T+D)","low":275.44,"open":275.53,"time":"201708032310","type":1,"volume":284,"ovolume":20162,"ctime":1501690518000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501772991000,"rose":0.03,"roseF":0.09,"average":275.16,"quoteDate":"20170803","quoteTime":"23:09:51","sequenceNo":45514},{"id":81708,"close":275.38,"high":275.51,"instID":"Au(T+D)","low":275.38,"open":275.45,"time":"201708032315","type":1,"volume":412,"ovolume":20574,"ctime":1501690878000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501773280000,"rose":0.03,"roseF":0.07,"average":275.16,"quoteDate":"20170803","quoteTime":"23:14:40","sequenceNo":46674},{"id":81724,"close":275.42,"high":275.44,"instID":"Au(T+D)","low":275.36,"open":275.36,"time":"201708032320","type":1,"volume":138,"ovolume":20712,"ctime":1501691175000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501773597000,"rose":-0.01,"roseF":-0.04,"average":275.17,"quoteDate":"20170803","quoteTime":"23:19:57","sequenceNo":47748},{"id":81743,"close":275.65,"high":275.66,"instID":"Au(T+D)","low":275.4,"open":275.42,"time":"201708032330","type":1,"volume":898,"ovolume":21610,"ctime":1501691720000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774199000,"rose":-0.08,"roseF":-0.23,"average":275.18,"quoteDate":"20170803","quoteTime":"23:29:59","sequenceNo":49825},{"id":81781,"close":275.65,"high":275.66,"instID":"Au(T+D)","low":275.5,"open":275.65,"time":"201708032335","type":1,"volume":392,"ovolume":22002,"ctime":1501692195000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774499000,"rose":0,"roseF":0,"average":275.19,"quoteDate":"20170803","quoteTime":"23:34:59","sequenceNo":51059},{"id":81797,"close":275.57,"high":275.65,"instID":"Au(T+D)","low":275.55,"open":275.6,"time":"201708032340","type":1,"volume":132,"ovolume":22134,"ctime":1501692439000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501774798000,"rose":0.03,"roseF":0.08,"average":275.19,"quoteDate":"20170803","quoteTime":"23:39:58","sequenceNo":52170},{"id":81817,"close":275.49,"high":275.58,"instID":"Au(T+D)","low":275.49,"open":275.57,"time":"201708032345","type":1,"volume":94,"ovolume":22228,"ctime":1501692679000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775092000,"rose":0.03,"roseF":0.08,"average":275.19,"quoteDate":"20170803","quoteTime":"23:44:52","sequenceNo":53126},{"id":81830,"close":275.42,"high":275.52,"instID":"Au(T+D)","low":275.41,"open":275.49,"time":"201708032350","type":1,"volume":106,"ovolume":22334,"ctime":1501692857000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775395000,"rose":0.03,"roseF":0.07,"average":275.19,"quoteDate":"20170803","quoteTime":"23:49:55","sequenceNo":54042},{"id":81849,"close":275.52,"high":275.52,"instID":"Au(T+D)","low":275.41,"open":275.42,"time":"201708032355","type":1,"volume":60,"ovolume":22394,"ctime":1501692983000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775676000,"rose":-0.04,"roseF":-0.1,"average":275.19,"quoteDate":"20170803","quoteTime":"23:54:36","sequenceNo":54805},{"id":81863,"close":275.55,"high":275.55,"instID":"Au(T+D)","low":275.45,"open":275.5,"time":"201708032360","type":1,"volume":112,"ovolume":22506,"ctime":1501693164000,"years":"2017","months":"08","days":"03","weeks":null,"hours":"23","utime":1501775994000,"rose":-0.01,"roseF":-0.03,"average":275.2,"quoteDate":"20170803","quoteTime":"23:59:54","sequenceNo":55583}]
         * vom : 2004
         */

        private String vom;
        private List<TdKxtsBean> TdKxts;

        public String getVom() {
            return vom;
        }

        public void setVom(String vom) {
            this.vom = vom;
        }

        public List<TdKxtsBean> getTdKxts() {
            return TdKxts;
        }

        public void setTdKxts(List<TdKxtsBean> TdKxts) {
            this.TdKxts = TdKxts;
        }

        public static class TdKxtsBean {
            /**
             * id : 83048
             * close : 274.36
             * high : 274.38
             * instID : Au(T+D)
             * low : 274.35
             * open : 274.38
             * time : 201708031350
             * type : 1
             * volume : 658
             * ovolume : 55838
             * ctime : 1501739417000
             * years : 2017
             * months : 08
             * days : 03
             * weeks : null
             * hours : 13
             * utime : 1501739398000
             * rose : 0.01
             * roseF : 0.04
             * average : 274.82
             * quoteDate : 20170803
             * quoteTime : 13:49:58
             * sequenceNo : 103634
             */

            private String id;
            private String close;
            private String high;
            private String instID;
            private String low;
            private String open;
            private String time;
            private String type;
            private String volume;
            private String ovolume;
            private String ctime;
            private String years;
            private String months;
            private String days;
            private Object weeks;
            private String hours;
            private String utime;
            private String rose;
            private String roseF;
            private String average;
            private String quoteDate;
            private String quoteTime;
            private String sequenceNo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getClose() {
                return close;
            }

            public void setClose(String close) {
                this.close = close;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getInstID() {
                return instID;
            }

            public void setInstID(String instID) {
                this.instID = instID;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getOpen() {
                return open;
            }

            public void setOpen(String open) {
                this.open = open;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getOvolume() {
                return ovolume;
            }

            public void setOvolume(String ovolume) {
                this.ovolume = ovolume;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getYears() {
                return years;
            }

            public void setYears(String years) {
                this.years = years;
            }

            public String getMonths() {
                return months;
            }

            public void setMonths(String months) {
                this.months = months;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public Object getWeeks() {
                return weeks;
            }

            public void setWeeks(Object weeks) {
                this.weeks = weeks;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            public String getRose() {
                return rose;
            }

            public void setRose(String rose) {
                this.rose = rose;
            }

            public String getRoseF() {
                return roseF;
            }

            public void setRoseF(String roseF) {
                this.roseF = roseF;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public String getQuoteDate() {
                return quoteDate;
            }

            public void setQuoteDate(String quoteDate) {
                this.quoteDate = quoteDate;
            }

            public String getQuoteTime() {
                return quoteTime;
            }

            public void setQuoteTime(String quoteTime) {
                this.quoteTime = quoteTime;
            }

            public String getSequenceNo() {
                return sequenceNo;
            }

            public void setSequenceNo(String sequenceNo) {
                this.sequenceNo = sequenceNo;
            }
        }
    }
}
