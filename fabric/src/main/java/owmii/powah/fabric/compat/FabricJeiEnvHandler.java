package owmii.powah.fabric.compat;

import dev.architectury.fluid.FluidStack;
import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.runtime.IIngredientManager;
import owmii.powah.compat.jei.JeiEnvHandler;

import java.util.stream.Stream;

public class FabricJeiEnvHandler implements JeiEnvHandler {
	@Override
	public Stream<FluidStack> getAllFluidIngredients(IIngredientManager ingredientManager) {
		return ingredientManager.getAllIngredients(FabricTypes.FLUID_STACK)
				.stream()
				.map(ingr -> FluidStack.create(ingr.getFluid(), ingr.getAmount(), ingr.getTag().orElse(null)));
	}
}
