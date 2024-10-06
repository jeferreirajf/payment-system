type Headers = {
  [key: string]: string;
};

export type Options = {
  headers: Headers;
  queryParams?: URLSearchParams;
};

export type HttpClientPostParams = {
  endpoint: string;
  options: Options;
  data: any;
};

export type HttpResponse<ResponseBody> = {
  statusCode: number;
  body: ResponseBody;
};
