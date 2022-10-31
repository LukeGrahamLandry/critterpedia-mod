package ca.lukegrahamlandry.critterpedia.base.api;

import java.util.List;

public interface CritterPlugin {
    List<CritterType> getTypes();

    List<CritterCategory> getCategories();

    String getId();
}
