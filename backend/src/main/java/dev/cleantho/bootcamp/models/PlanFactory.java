package dev.cleantho.bootcamp.models;

/**
 * 
 * Fábrica de criação do serviço de Telefonia ou Internet
 * 
 * @author Cleantho
 */
public class PlanFactory {
	public static Plan createPlan(String type) {
        if (type.equalsIgnoreCase("internet")) {
            return new InternetPlan();
        } else if (type.equalsIgnoreCase("phone")) {
            return new PhonePlan();
        }
        return null;
    }
}
