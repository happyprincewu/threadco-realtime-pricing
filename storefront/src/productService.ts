import axios from "axios";

export type LivePriceUpdateEvent = { sku: string; store: string; price: number; status: string };

const SSE_URL = import.meta.env.VITE_KAFKA_SSE_URL as string;

/** Subscribes the store and smart-mirror pages to live price updates streamed from Kafka. */
export function subscribeToLivePriceUpdatesFromKafka(onUpdate: (e: LivePriceUpdateEvent) => void): () => void {
  const source = new EventSource(SSE_URL);
  source.onmessage = (msg) => onUpdate(JSON.parse(msg.data) as LivePriceUpdateEvent);
  return () => source.close();
}

export async function getProducts(city: string) {
  const res = await axios.get(`/api/products?city=${encodeURIComponent(city)}`);
  return res.data;
}
