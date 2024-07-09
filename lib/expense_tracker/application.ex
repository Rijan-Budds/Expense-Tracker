defmodule ExpenseTracker.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      ExpenseTrackerWeb.Telemetry,
      ExpenseTracker.Repo,
      {DNSCluster, query: Application.get_env(:expense_tracker, :dns_cluster_query) || :ignore},
      {Phoenix.PubSub, name: ExpenseTracker.PubSub},
      # Start a worker by calling: ExpenseTracker.Worker.start_link(arg)
      # {ExpenseTracker.Worker, arg},
      # Start to serve requests, typically the last entry
      ExpenseTrackerWeb.Endpoint
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: ExpenseTracker.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  @impl true
  def config_change(changed, _new, removed) do
    ExpenseTrackerWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
