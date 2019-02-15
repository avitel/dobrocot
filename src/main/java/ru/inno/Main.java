package ru.inno;
import ru.inno.dao.*;
import ru.inno.entity.Class;
import ru.inno.entity.Color;
import ru.inno.entity.Engine;
import ru.inno.entity.Mark;
import ru.inno.entity.Model;
import ru.raven.ConnectionController;
public class Main {
    public static void main(String[] args) {
        ConnectionController cc = ConnectionController.createController();
        cc.setConnection();
//        ModelDAO model = new ModelImpl(cc.getConnection());
//        model.addModel(2,"VAZ");
        //System.out.println(model.getModel(1).getName());

//        for(Model m : model.getModels())
//            System.out.println(m.getName()+ m.getId()+ m.getId_mark());
//        MarkDAO mark = new MarkImpl(cc.getConnection());
        //mark.addMark("BMW");
        //System.out.println(mark.getMark(1).getName());
//        for(Mark m : mark.getMarks())
//            System.out.println(m.getName()+ m.getId());

//        EngineDAO engine = new EngineImpl(cc.getConnection());
//        engine.addEngine("1.1 TURBO");
//        System.out.println(engine.getEngine(1).getName());
//        for(Engine m : engine.getEngines())
//            System.out.println(m.getName()+ m.getId());

//        ColorDAO color = new ColorImpl(cc.getConnection());
//        color.addColor("black");
//        System.out.println(color.getColor(1).getName());
//        for(Color m : color.getColors())
//            System.out.println(m.getName()+ m.getId());

        ClassDAO classdao = new ClassImpl(cc.getConnection());
//        classdao.addClass("C");
//        classdao.addClass("S");
//        System.out.println(classdao.getClass(1).getName());
        for(Class m : classdao.getClasses())
            System.out.println(m.getName()+ m.getId());
    }
}
