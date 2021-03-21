package pl.coderslab.myapp.usage.usageDTO;

import lombok.Data;

@Data
public class UsageDTO {

    private long id;

    private int year;

    private int month;

    private double waterUsage;

    private String waterUsageSymbol;

    private String waterDescription;

    private double gasUsage;

    private String gasUsageSymbol;

    private String gasDescription;

    private double electricityUsage;

    private String electricityUsageSymbol;

    private String electricityDescription;
}
