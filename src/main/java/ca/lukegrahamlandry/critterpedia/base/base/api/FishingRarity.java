package ca.lukegrahamlandry.critterpedia.base.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

public class FishingRarity {
    public final int color;
    public final float barSize;
    public final int weight;
    public final float fishSpeed;
    public final float fishMoveChance;
    public final float barGravity;
    public final float progressGainRate;
    public final float progressLossRate;
    public final float barForce;

    public FishingRarity(JsonObject json){
        this.color = 0; // TODO
        this.barSize = json.get("barSize").getAsFloat();
        this.weight = json.get("weight").getAsInt();
        this.fishSpeed = json.get("fishSpeed").getAsFloat();
        this.fishMoveChance = json.get("fishMoveChance").getAsFloat();
        this.barGravity = json.get("barGravity").getAsFloat();
        this.progressGainRate = json.get("progressGainRate").getAsFloat();
        this.progressLossRate = json.get("progressLossRate").getAsFloat();
        this.barForce = json.get("barForce").getAsFloat();
    }

    public FishingRarity(String data){
        this(GsonHelper.parse(data));
    }

    static final Gson GSON = new Gson();
    public String toJson(){
        JsonObject data = new JsonObject();
        data.addProperty("color", this.color);
        data.addProperty("barSize", this.barSize);
        data.addProperty("weight", this.weight);
        data.addProperty("fishSpeed", this.fishSpeed);
        data.addProperty("fishMoveChance", this.fishMoveChance);
        data.addProperty("barGravity", this.barGravity);
        data.addProperty("progressGainRate", this.progressGainRate);
        data.addProperty("progressLossRate", this.progressLossRate);
        data.addProperty("barForce", this.barForce);
        return GSON.toJson(data);
    }

    @Override
    public String toString() {
        return "FishingRarity{" +
                "color=" + color +
                ", barSize=" + barSize +
                ", weight=" + weight +
                ", fishSpeed=" + fishSpeed +
                ", fishMoveChance=" + fishMoveChance +
                ", barGravity=" + barGravity +
                ", progressGainRate=" + progressGainRate +
                ", progressLossRate=" + progressLossRate +
                ", barForce=" + barForce +
                '}';
    }
}
