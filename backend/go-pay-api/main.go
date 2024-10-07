package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"math/rand"
	"net/http"
	"time"
)

type RequestBody struct {
	PaymentId   string  `json:"paymentId"`
	Amount      float64 `json:"amount"`
	CallbackUrl string  `json:"callback"`
}

func main() {
	server := http.NewServeMux()

	server.HandleFunc("/orders/place", orderPlaceHandler)

	http.ListenAndServe(":6001", server)
}

func orderPlaceHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var req RequestBody
	err := json.NewDecoder(r.Body).Decode(&req)

	if err != nil {
		http.Error(w, "Invalid request body", http.StatusBadRequest)
		return
	}

	go httpCallback(req)

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
}

func httpCallback(request RequestBody) {
	<-time.After(5 * time.Second)

	statusPossibilities := []string{"APPROVED", "REJECTED"}
	status := statusPossibilities[rand.Intn(len(statusPossibilities))]

	http.Post(request.CallbackUrl, "application/json", nil)

	body := []byte(fmt.Sprintf(`{"paymentId": "%s", "status": "%s"}`, request.PaymentId, status))

	buffer := bytes.NewBuffer(body)

	http.Post(request.CallbackUrl, "application/json", buffer)
}
