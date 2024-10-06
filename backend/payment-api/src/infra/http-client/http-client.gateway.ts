import { HttpClientPostParams, HttpResponse } from './http-client.dto';

export interface HttpClient {
  post<ResponseBody>(
    params: HttpClientPostParams,
  ): Promise<HttpResponse<ResponseBody>>;
}
