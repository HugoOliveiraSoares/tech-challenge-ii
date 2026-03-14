package br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects;

public record UserOwner(Long id,
                        String userType) {
    public boolean isOwner() {
        return "owner".equalsIgnoreCase(userType);
    }
}
