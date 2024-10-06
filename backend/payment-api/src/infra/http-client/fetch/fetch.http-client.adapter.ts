import {
  HttpClientPostParams,
  HttpResponse,
  Options,
} from '../http-client.dto';
import { HttpClient } from '../http-client.gateway';
import { HttpClientException } from '../exceptions/http-client.exception';
import { FetchHttpClientBuildDto } from './fetch.http-client.dto';

export class FetchHttpClientAdapter implements HttpClient {
  private constructor(private readonly baseUrl: string) {}

  private createSearchUrl(endpoint: string, params: URLSearchParams): URL {
    const searchUrl = new URL(`${this.baseUrl}${endpoint}`);

    params &&
      params.forEach((value, key) => {
        searchUrl.searchParams.append(key, value);
      });

    return searchUrl;
  }

  public static build({
    baseUrl,
  }: FetchHttpClientBuildDto): FetchHttpClientAdapter {
    return new FetchHttpClientAdapter(baseUrl);
  }

  public async post<ResponseBody>({
    endpoint,
    options,
    data,
  }: HttpClientPostParams): Promise<HttpResponse<ResponseBody>> {
    this.logRequest(endpoint, 'POST', options, data);

    const url = this.createSearchUrl(endpoint, options.queryParams);

    try {
      const response = await fetch(url.toString(), {
        headers: options.headers,
        method: 'POST',
        body: data ? JSON.stringify(data) : '',
      });

      if (!response.ok) {
        const errorMessage = await this.getResponseErrorMessage(response);

        throw new HttpClientException(
          `Error fetching data - ${response.statusText} ${errorMessage}`.trim(),
          FetchHttpClientAdapter.name,
        );
      }

      const responseData = (await this.getResponseData(
        response,
      )) as ResponseBody;

      const output: HttpResponse<ResponseBody> = {
        body: responseData,
        statusCode: response.status,
      };

      return output;
    } catch (error) {
      throw error;
    }
  }

  private async getResponseData(response: Response) {
    const textData = await response.text();
    const data = textData ? JSON.parse(textData) : undefined;
    return data;
  }

  private async getResponseErrorMessage(response: Response) {
    const textData = await response.text();
    const data = textData ? JSON.parse(textData) : undefined;
    const errorMessage = data?.message || '';
    return JSON.stringify(errorMessage);
  }

  private logRequest(url: string, method: string, options: Options, data: any) {
    console.log('🚀 ~ Method ', method);
    console.log('🚀 ~ Fetching to ', `${this.baseUrl}${url}`);
    console.log('🚀 ~ Using options ', options);
    console.log('🚀 ~ Sending data ', data);
  }
}
