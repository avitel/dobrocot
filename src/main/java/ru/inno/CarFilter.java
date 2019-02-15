package ru.inno;

import ru.inno.entity.*;
import ru.inno.entity.Class;

public class CarFilter {


    private Color color;
    private Engine engine;
    private Mark mark;
    private Model model;
    private Class carClass;



    public void setColor(Color color) {
        this.color = color;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setCarClass(Class carClass) {
        this.carClass = carClass;
    }



    public Color getColor() {
        return color;
    }

    public Engine getEngine() {
        return engine;
    }

    public Mark getMark() {
        return mark;
    }

    public Model getModel() {
        return model;
    }

    public Class getCarClass() {
        return carClass;
    }



    public String getSQLquery(){

        String GET_FILTERED_CARS_SQL_TEMPLATE =
                "select * from car as car "
                        +"left join person as per"
                +"on car.person_is = per.id";

        StringBuilder sb = new StringBuilder(GET_FILTERED_CARS_SQL_TEMPLATE);

        sb.append("\nwhere");


        if (color != null) {
            sb.append("\ncolor_id = "+color.getId()+" and");
        }

        if (engine != null) {
            sb.append("\nengine_id = "+engine.getId()+" and");
        }

        if (mark != null) {
            sb.append("\nmark_id = "+mark.getId()+" and");
        }

        if (model != null) {
            sb.append("\nmodel_id = "+model.getId()+" and");
        }

        if (carClass != null) {
            sb.append("\nclass_id = "+carClass.getId());
        }


        return sb.toString();
    }
}
