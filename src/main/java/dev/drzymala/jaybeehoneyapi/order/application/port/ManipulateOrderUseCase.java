package dev.drzymala.jaybeehoneyapi.order.application.port;

import dev.drzymala.jaybeehoneyapi.commons.Either;
import dev.drzymala.jaybeehoneyapi.order.domain.Delivery;
import dev.drzymala.jaybeehoneyapi.order.domain.OrderStatus;
import dev.drzymala.jaybeehoneyapi.order.domain.Recipient;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ManipulateOrderUseCase {

    PlaceOrderResponse placeOrder(PlaceOrderCommand command);

    void deleteOrderById(Long id);

    UpdateStatusResponse updateOrderStatus(UpdateStatusCommand command);

    @Builder
    @Value
    @AllArgsConstructor
    class PlaceOrderCommand {
        @Singular
        List<OrderItemCommand> items;
        Recipient recipient;
        @Builder.Default
        Delivery delivery = Delivery.COURIER;
    }

    @Value
    class OrderItemCommand {

        Long honeyId;
        int quantity;

        @SneakyThrows
        public OrderItemCommand(Long honeyId, int quantity) {
            this.honeyId = honeyId;
            if (quantity <= 0) {
                throw new Exception("The ordered quantity cannot be equal or less than zero");
            }
            this.quantity = quantity;
        }
    }

    @Value
    class UpdateStatusCommand {

        Long orderId;

        OrderStatus status;
    }

    class PlaceOrderResponse extends Either<String, Long> {
        public PlaceOrderResponse(boolean success, String left, Long right) {
            super(success, left, right);
        }

        public static PlaceOrderResponse success(Long orderId) {
            return new PlaceOrderResponse(true, null, orderId);
        }

        public static PlaceOrderResponse failure(String error) {
            return new PlaceOrderResponse(false, error, null);
        }
    }

    class UpdateStatusResponse extends Either<Error, OrderStatus> {
        public UpdateStatusResponse(boolean success, Error left, OrderStatus right) {
            super(success, left, right);
        }

        public static UpdateStatusResponse success(OrderStatus status) {
            return new UpdateStatusResponse(true, null, status);
        }

        public static UpdateStatusResponse failure(Error error) {
            return new UpdateStatusResponse(false, error, null);
        }
    }

    @AllArgsConstructor
    @Getter
    enum Error {
        NOT_FOUND(HttpStatus.NOT_FOUND),
        FORBIDDEN(HttpStatus.FORBIDDEN);

        private final HttpStatus status;
    }
}
