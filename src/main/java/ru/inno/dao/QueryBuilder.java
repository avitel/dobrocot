package ru.inno.dao;

public class QueryBuilder {
    
    private Integer color;
    private Integer engine;
    private Integer mark;
    private Integer model;
    private Integer carClass;

    public static final String GET_FILTERED_CARS_SQL_TEMPLATE =
            "select car.id, \n" +
                    "owner_id, \n" +
                    "mark_id, \n" +
                    "model_id, \n" +
                    "assembledate, \n" +
                    "engine_id, \n" +
                    "numberofseats, \n" +
                    "color_id, \n" +
                    "person.name person_name, \n" +
                    "mark.name mark_name, \n" +
                    "model.name model_name, \n" +
                    "engine.name engine_name, \n" +
                    "color.name color_name \n" +
                    "from car \n" +
                    "left join person on car.owner_id = person.id \n" +
                    "left join mark on car.mark_id = mark.id \n" +
                    "left join model on car.model_id = model.id \n" +
                    "left join engine on car.engine_id = engine.id \n" +
                    "left join color on car.color_id = color.id";


    public QueryBuilder(Integer color, Integer engine, Integer mark, Integer model, Integer carClass) {
        this.color = color;
        this.engine = engine;
        this.mark = mark;
        this.model = model;
        this.carClass = carClass;
    }

    public QueryBuilder(Integer color, Integer engine, Integer mark, Integer model) {
        this.color = color;
        this.engine = engine;
        this.mark = mark;
        this.model = model;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public void setCarClass(Integer carClass) {
        this.carClass = carClass;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getEngine() {
        return engine;
    }

    public Integer getMark() {
        return mark;
    }

    public Integer getModel() {
        return model;
    }

    public Integer getCarClass() {
        return carClass;
    }

    
    public String getSQLquery(){

        
        StringBuilder sb = new StringBuilder(GET_FILTERED_CARS_SQL_TEMPLATE);

        sb.append("\nwhere true and");
        
        if (color != null) {
            sb.append("\ncolor_id = "+color+" and");
        }

        if (engine != null) {
            sb.append("\nengine_id = "+engine+" and");
        }

        if (mark != null) {
            sb.append("\nmark_id = "+mark+" and");
        }

        if (model != null) {
            sb.append("\nmodel_id = "+model+" and");
        }

        if (carClass != null) {
            sb.append("\nclass_id = "+carClass+ " and");
        }

        sb.delete(sb.length()-3, sb.length());

        return sb.toString();
    }
}
